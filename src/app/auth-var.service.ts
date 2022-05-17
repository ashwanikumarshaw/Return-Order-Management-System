import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthVarService {

  public token!: string;
  public username!: string;
  constructor() { }
}
