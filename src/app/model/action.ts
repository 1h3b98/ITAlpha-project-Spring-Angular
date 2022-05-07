import { User } from "./user";

export class Actions {
	'actionId': number;
    'actionType':string;
    'comment': string;
	'likeStatus' : boolean;
	'favStatus': boolean;
	'joinStatus': boolean;
    'recieverId':number;
	'accepted':boolean;
	'date': Date;
	'user':User;
}