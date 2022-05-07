export class User{
   
    userId: number;
    firstName: string;
    lastName: string;
    password:string; 
    isBlocked: boolean;
    sexe: number;
    email: string;
    username: string;
    birthDate: Date;
    phonenumbr: number;
    jobTitle: string;
    picture: string;
    hobbies: string;
    adresse: string
    bio: string
    oneTimePassword: boolean;
    points: number;
    locked: boolean;
    enabled: boolean;
    roles: string[];
    department:string;
    cin: number;
    //authorities: 
    accountNonExpired: true;
    credentialsNonExpired: true;
    accountNonLocked: true;
}
