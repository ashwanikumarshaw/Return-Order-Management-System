import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RestapiService } from '../restapi.service';
import { ProcessDetail } from '../process-detail';
import { AuthVarService } from '../auth-var.service';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  processDetailValue = new ProcessDetail();

  msg = '';
  ComponentType = [
    { id: 1, name: "INTEGRAL" },
    { id: 2, name: "ACCESSORY" }
  ];
  processdetail = this.fb.group({
    userName: new FormControl(''),
    contactNumber: new FormControl(''),
    componentName: new FormControl(''),
    componentType: new FormControl(''),
    quantity: new FormControl(''),
    isPriorityRequest: new FormControl(''),


  });

  constructor(private service: RestapiService, private router: Router, private authVar: AuthVarService, private fb: FormBuilder) {
    //this.processDetailValue.userName = authVar.username;
    this.processDetailValue.userName = '' + sessionStorage.getItem('username');

    console.log(sessionStorage.getItem('token'));

    this.getProcessDetail();
  }

  ngOnInit(): void {
    console.log(history.state);
  }

  cal: boolean = false;
  public response: any;

  calProcessDetail() {
    console.log(this.processdetail.get('isPriorityRequest')?.touched);
    if (!this.processdetail.get('isPriorityRequest')?.touched) {
      this.processdetail.patchValue({ 'isPriorityRequest': false })
    }
    console.log("processdetail userName" + this.processdetail.get('userName')?.value);
    console.log(this.processdetail.value);
    this.processDetailValue = this.processdetail.value;

    this.processDetailValue.userName = '' + sessionStorage.getItem('username');
    this.service.processDetail('' + sessionStorage.getItem('token'), this.processDetailValue).subscribe(
      apiresponse => {
        this.response = apiresponse;
        this.cal = true;
        console.log("calProcessDetail" + this.cal);
        this.getProcessDetail();
      },
      error => {
        console.log("Exception Occured" + error)
        this.msg = "invalid credentials. please try again";
      }
    );
  }
  public getProcess: boolean = false;
  public allResponse: any;
  getProcessDetail() {

    this.service.getProcessDetail('' + sessionStorage.getItem('token'), '' + sessionStorage.getItem('username')).subscribe(
      apiresponse => {
        this.allResponse = apiresponse;
        this.getProcess = true;
        console.log("calProcessDetail" + this.getProcess);
      },
      error => {
        console.log("Exception Occured" + error)
        this.msg = "invalid credentials. please try again";
      }
    );
  }


  displayStyle = "none";
  requestId = "";
  processingCharge = "";
  openPopup(result: any) {
    console.log("advfjabjsb " + result.requestId);
    this.requestId = result.requestId;
    this.processingCharge = result.processingCharge;
    this.displayStyle = "block";
  }
  closePopup() {
    this.displayStyle = "none";
  }

  paymentForm = this.fb.group({
    requestId: new FormControl(''),
    processingCharge: new FormControl(''),
    cardNumber: new FormControl(''),
    creditLimit: new FormControl(''),
  });
  pay() {
    this.service.CompleteProcessing('' + sessionStorage.getItem('token'),
      this.requestId,
      this.paymentForm.get('cardNumber')?.value,
      this.paymentForm.get('creditLimit')?.value,
      this.processingCharge).subscribe(
        apiresponse => {
          console.log("Processing" + apiresponse);
          alert("Processing :: " + apiresponse);
          this.closePopup();
          this.getProcessDetail();
        },
        error => {
          console.log("Exception Occured" + error)
        }
      )
  }
}
