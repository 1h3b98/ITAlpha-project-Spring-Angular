import { Injectable } from "@angular/core";

@Injectable()
export class userBadge{
    userId:number;
        username: string;
        points: number;
        idBadge:number;
        dateBadge: Date;
        badge: string;
        fname: string;
        lname: string;


        constructor() { }
}