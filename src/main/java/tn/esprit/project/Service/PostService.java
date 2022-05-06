package tn.esprit.project.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.project.Entities.*;
import tn.esprit.project.Repository.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PostService implements  IPostService{

    @Autowired
    PostRepository pr ;
    @Autowired
    UserRepo ur ;
    @Autowired
    RatinggRepository rr;
    @Autowired
    FavoritesArticleRepository ftr;
    @Autowired
    SignalerRepository sr;
    @Autowired
    NotificationRepository nr ;
    @Autowired
    private FileDbRepository fileDbRepository;
    @Autowired
    LikePostRepository lp ;

    @Override
    public Post getPost(Long id) {
        return pr.findById(id).get();
    }

    @Override
    public List<Post> getPosts() {
        List<Post> postes = (List<Post>) pr.findAll();
        return postes;
    }

    @Override
    public FileResponse addPost(String content, Long idUser,MultipartFile file) throws IOException {
        Post p = new Post();
        p.setContent(content);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        p.setCreateAt(timestamp);
        User user = ur.findById(idUser).get();
        p.setUserP(user);
         pr.save(p);
       return storefilePoste(file,p);
    }

    @Override
    public Post updatePost(Post p, Long id) {
       Post post = pr.findById(id).get();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        post.setUpdateAt(timestamp);
        return pr.save(post);
    }

    @Override
    public void deletePost(Long id) {
        Post post = pr.findById(id).get();
        pr.delete(post);
    }

    @Override
    public List<Post>  getPostByUser(Long idUser) {
        List<Post> post = new ArrayList<>();
        List<Post> posts = (List<Post>) pr.findAll();
        for (Post p:posts) {
            if (p.getUserP().getUserId()==idUser)
                post.add(p);
        }
        return post;
    }

    public List<Post> getmemories(Long id){
       User user = ur.findById(id).get();
        List<Post> posts = user.getPosts();
        List<Post> memoriespost= new ArrayList<>();

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        Calendar calposts = Calendar.getInstance();
        cal.setTime(date);
        int nbr=0;
        int year =cal.get(Calendar.YEAR);
        int dat = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        log.info(String.valueOf(year));
        log.info(String.valueOf(dat));
        log.info(String.valueOf(month));
        for (Post p: posts) {
            calposts.setTime(p.getCreateAt());
            int yearp = calposts.get(Calendar.YEAR);
            int dayp = calposts.get(Calendar.DAY_OF_MONTH);
            int monthp =calposts.get(Calendar.MONTH);
            log.info("-------------------");
            log.info(String.valueOf(yearp));
            log.info(String.valueOf(dayp));
            log.info(String.valueOf(monthp));
            if(year>yearp && dayp ==dat && monthp==month){
                memoriespost.add(p);
                nbr++;}}
        Notification notification =new Notification();
        notification.setContent("tu à : "+nbr+" Postes");
        notification.setUser(user);
        nr.save(notification);
    return memoriespost;
    }

    public void SignalerPost(Signaler signaler , Long idpost , Long iduser) {
        User user = ur.findById(iduser).get();
        Post post = pr.findById(idpost).get();
        Signaler signalerf = new Signaler();
        SignalerId signalerId = new SignalerId();
        signalerId.setPost(post);
        signalerId.setUser(user);
        signalerf.setDescription(signaler.getDescription());
        signalerf.setSingalerType(signaler.getSingalerType());
        signalerf.setSignalerId(signalerId);
        sr.save(signalerf);
    }

    /************************************ file **********************************/

    public FileResponse storefilePoste(MultipartFile file, Post post) throws IOException {
        String fileName = file.getOriginalFilename();
        FileDb fileDb = new FileDb(UUID.randomUUID().toString(), fileName, file.getContentType(), file.getBytes(), post);
        fileDbRepository.save(fileDb);
        return  mapToFileResponse(fileDb);
    }

    public FileDb getFileById(String id) {

        Optional<FileDb> fileOptional = fileDbRepository.findById(id);

        if(fileOptional.isPresent()) {
            return fileOptional.get();
        }
        return null;
    }

    public List<FileResponse> getFileList(){
        return fileDbRepository.findAll().stream().map(this::mapToFileResponse).collect(Collectors.toList());
    }

    private FileResponse mapToFileResponse(FileDb fileDb) {
        return new FileResponse(fileDb.getId(), fileDb.getType(), fileDb.getName(), fileDb.getPost());
    }

    /************************ LIKE *************************************/

    @Override
    public LikePost makelikePost(Long idpost, Long idUser) {
        LikePost likep = new LikePost();
        LikePostId likeId = new LikePostId();
        likeId.setUser(ur.findById(idUser).orElse(null));
        likeId.setPost(pr.findById(idpost).orElse(null));
        likep.setLikepostId(likeId);
        likep.setValue(true);
        return lp.save(likep);
    }

    @Override
    public LikePost  makedislikePost(Long idpost, Long idUser) {
        LikePost likep = new LikePost();
        LikePostId likeId = new LikePostId();
        likeId.setUser(ur.findById(idUser).orElse(null));
        likeId.setPost(pr.findById(idpost).orElse(null));
        likep.setLikepostId(likeId);
        likep.setValue(false);
        return lp.save(likep);
    }

    @Override
    public void removeLike(Long idpost, Long idUser) {
        LikePostId likeId = new LikePostId();
        likeId.setPost(pr.findById(idpost).orElse(null));
        likeId.setUser(ur.findById(idUser).orElse(null));
        lp.deleteById(likeId);
    }

    @Override
    public Map<Post, Integer> getPostLike() {
        Map<Post,Integer> map=new HashMap<>();
        Set<Post> posts=lp.getReactedPosts();
        posts.forEach(p->map.put(p,lp.countnblikebypost(p)));
        return map;
    }

    @Override
    public Post mostLikedPost() {
        Set<Post> posts=lp.getReactedPosts();
        return  posts.stream().max((p,v)->lp.countnblikebypost(p)-lp.countnblikebypost(v)).orElse(null);
    }

    @Override
    public Set<Post> treeWithLike() {
        Set<Post> posts=lp.getReactedPosts();
        return  posts.stream().sorted((v,p)->lp.countnblikebypost(p)-lp.countnbdeslikebypost(v)).collect(Collectors.toSet());
    }

    @Override
    public Set<Post> negativePosts() {
        Set<Post> posts=lp.getReactedPosts();
        return posts.stream().filter(p->lp.countnblikebypost(p)<lp.countnbdeslikebypost(p)).collect(Collectors.toSet());
    }

    @Override
    public int getNbLike(Post p) {
        if(lp.countnblikebypost(p)!=0)
            return  lp.countnblikebypost(p);
        return 0;
    }

    @Override
    public int getNbDeslike(Post post) {
        if(lp.countnbdeslikebypost(post)!=0)
            return lp.countnbdeslikebypost(post);
        return 0;
    }

    @Override
    public Map<Post, Integer> getPostDeslike() {
        Map<Post,Integer> map=new HashMap<>();
        Set<Post> posts=lp.getReactedPosts();
        posts.forEach(p->map.put(p,lp.countnbdeslikebypost(p)));
        return map;
    }

}
