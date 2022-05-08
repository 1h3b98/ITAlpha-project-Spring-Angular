import { Pipe, PipeTransform } from '@angular/core';
import { FeedBack } from '../model/FeedBack';

@Pipe({
  name: 'filterFeedback'
})
export class FilterFeedbackPipe implements PipeTransform {
  transform(value: any, filterString: string) {
    if (value==null || filterString === '') {
      return value;
    
    }

    const listf = [];
    for (const feed  of value) {
      const str  = feed['username']
      if (str.includes(filterString)) {
        console.log(filterString)


        listf.push(feed);
      }
    }
  

    return listf;

  }

  transform1(value: any, filterString: string) {
    if (value==null || filterString === '') {
      return value;
    
    }

    const listfs = [];
    for (const feedsent  of value) {
      const str  = feedsent['username']
      if (str.includes(filterString)) {
        console.log(filterString)


        listfs.push(feedsent);
      }
      console.log(listfs)
    }
  

    return listfs;

  }



}
