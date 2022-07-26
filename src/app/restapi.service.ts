import { HttpClient, HttpErrorResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { User } from './user';
import { ProcessDetail } from './process-detail';


@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  constructor(private http: HttpClient) { }

  public login(user: User): Observable<any> {

    console.log('USer' + user.userName + "   " + user.userPassword + "" + user);
    const request = { userName: user.userName, userPassword: user.userPassword };
    console.log(request);

    let httpHeaders = new HttpHeaders();
    httpHeaders.set('Content-Type', 'application/json');
    return this.http.post<any>("http://localhost:9191/auth/login", request, { headers: httpHeaders });//http://3.87.91.156:9191/auth/login
  }


  public processDetail(jwtToken: string, processDetail: ProcessDetail): Observable<any> {

    console.log("ProcessDetail" + processDetail);

    let httpHeaders = new HttpHeaders()
      .set('Accept', 'application/json')
      .set('Authorization', `Bearer ${jwtToken}`);


    const request = {
      userName: processDetail.userName,
      componentName: processDetail.componentName,
      componentType: processDetail.componentType,
      quantity: processDetail.quantity,
      contactNumber: processDetail.contactNumber,
      isPriorityRequest: processDetail.isPriorityRequest
    };//http://localhost:9090/return/ProcessDetail
    //http://3.87.91.156:9191/return/ProcessDetail
    return this.http.request<any>("POST", "http://localhost:9090/return/ProcessDetail", {
      body: request,
      headers: httpHeaders,
      responseType: 'json'
    }).pipe(catchError(this.handleError));
  }

  public CompleteProcessing(jwtToken: string, requestId: string, cardNumber: string, creditLimit: string, processingCharge: string): Observable<any> {

    let httpHeaders = new HttpHeaders()
      .set('Accept', 'application/json')
      .set('Authorization', `Bearer ${jwtToken}`);

    return this.http.post<any>("http://localhost:9191/return/CompleteProcessing/" + requestId + "/" + cardNumber + "/" + creditLimit + "/" + processingCharge, null, {
      headers: httpHeaders,
    }).pipe(catchError(this.handleError));
  }

  public getProcessDetail(jwtToken: string, userName: string) {
    let httpHeaders = new HttpHeaders()
      .set('Accept', 'application/json')
      .set('Authorization', `Bearer ${jwtToken}`);
    return this.http.get<any>("http://localhost:9191/return/ProcessDetail/" + userName, {

      headers: httpHeaders,
      responseType: 'json'

    }).pipe(catchError(this.handleError));
  }

  handleError(err: any) {
    if (err instanceof HttpErrorResponse) {
      console.log("Serverside Error");
      window.alert("Serverside Error ::" + err.message);
    }
    else {
      console.log("Clientside Error");
      window.alert("Serverside Error ::" + err.message);
    }
    return throwError(err);
  }
}




