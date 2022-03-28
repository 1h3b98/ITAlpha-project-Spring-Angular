package tn.esprit.project.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.project.Entities.Event;
import tn.esprit.project.Entities.Notification;
import tn.esprit.project.Entities.User;
import tn.esprit.project.Repository.EventRepository;
import tn.esprit.project.Repository.NotificationRepository;
import tn.esprit.project.Repository.UserRepository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {
  @Autowired
    UserRepository ur ;
  @Autowired
    NotificationRepository nr ;
    @Autowired
    EventRepository er;

@Scheduled(fixedRate = 10000)
  public void birthdayUsers(){
    Long iduser = Long.valueOf(1);
      List<User>users = (List<User>) ur.findAll();
      Date newdate =new Date();
      Calendar c = Calendar.getInstance();
      c.setTime(newdate);
      int day = c.get(Calendar.DAY_OF_MONTH);
      int year = c.get(Calendar.YEAR);
      int month = c.get(Calendar.MONTH);
    log.info("day"+String.valueOf(day));
    log.info("year"+String.valueOf(year));
    log.info("month"+String.valueOf(month));
    log.info("------------------------------");
      Calendar c1 = Calendar.getInstance();
      for (User u:users) {
          c1.setTime(u.getBirthDate());
          int dayuser = c1.get(Calendar.DAY_OF_MONTH);
          int yearuser = c1.get(Calendar.YEAR);
          int monthuser = c1.get(Calendar.MONTH);
          log.info("day"+String.valueOf(dayuser));
          log.info("year"+String.valueOf(yearuser));
          log.info("month"+String.valueOf(monthuser));
          if(day==dayuser&& monthuser==month && u.getUserId()!=iduser){
              Notification notification = new Notification();
              notification.setUser(ur.findById(iduser).get());
              notification.setNotDate((new Timestamp(newdate.getTime())));
              notification.setContent("anniversaire de :"+u.getFName()+" -- age :"+(year-yearuser));
              nr.save(notification);
          }
          if(day==dayuser&& monthuser==month && u.getUserId()==iduser){
              Notification notification = new Notification();
              notification.setUser(ur.findById(iduser).get());
              notification.setNotDate((new Timestamp(newdate.getTime())));
              notification.setContent("ton anniversaire :"+u.getFName()+"-- age :"+(year-yearuser));
              nr.save(notification);
          }

      }
  }
    public User addFav(long eventId,long userId){
        User userToupdate;
        Event eventInuser;
        userToupdate=ur.findById(userId).orElse(null);
        eventInuser=er.findById(eventId).orElse(null);
        assert userToupdate != null;
        userToupdate.getFavEvents().add(eventInuser);
        return ur.save(userToupdate);
    }

    public User removeFav(long eventId,long userId){
        User userToupdate=ur.findById(userId).orElse(null);
        Event eventInuser=er.findById(eventId).orElse(null);
        assert userToupdate != null;
        userToupdate.getFavEvents().remove(eventInuser);
        return ur.save(userToupdate);
    }

}
