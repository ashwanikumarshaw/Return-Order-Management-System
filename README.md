# Return-Order-Management-System
A leading Supply chain Management Organization wants to automate the return orders, by classifying them to repair or replacement. 
Repair is for all main or integral part of their product. Replacement is for accessories.

## WorkFlow 

![IMG_0273](https://user-images.githubusercontent.com/35097948/168906599-0bd209a0-2297-46f1-8bf8-2cf04c6af6a5.JPG)

----------------------------------------  Requriment ---------------------------------------

API BaseURL : http://localhost:9191

Login EndPion: /auth/login
# Sample :

![image](https://user-images.githubusercontent.com/35097948/168907949-ccab0a1a-4017-47b7-bd06-c26933ee70e6.png)

Authentication EndPion: /auth/validate?userName=ashu123
# Sample :

![image](https://user-images.githubusercontent.com/35097948/168910211-adc2eb45-4f27-4e71-b783-1d2dba647360.png)

ProcessDetail EndPion: /return/ProcessDetail
# Sample :

![image](https://user-images.githubusercontent.com/35097948/168908457-ff5e6fd6-3cc4-4977-b3f2-08b669cb4b5c.png)

![image](https://user-images.githubusercontent.com/35097948/168908142-8747bd16-6dce-4c42-ac28-b345fede1274.png)

CompleteProcessing EndPion: /return/CompleteProcessing/{requestId}/{cardNumber}/{creditLimit}/{processingCharge}
# Sample :

![image](https://user-images.githubusercontent.com/35097948/168909456-2d9efbe8-aad2-463e-b074-5e762fa93a8f.png)

![image](https://user-images.githubusercontent.com/35097948/168909374-26861b2f-cff8-4cae-a4ab-a97841800f75.png)

----------------------------------------  Extra Features ---------------------------------------

ProcessDetails URL: http://localhost:9191/return/ProcessDetail/{userName}
# Sample :
![image](https://user-images.githubusercontent.com/35097948/168910845-1c08305c-d4a4-441b-a89a-85407c1ea7ec.png)

registerNewUser URL: http://localhost:9094/registerNewUser
# Sample :
![image](https://user-images.githubusercontent.com/35097948/168911178-f958e1c0-70f9-4ede-a949-6ddf4e980131.png)



----------------------------------------  UI Features ---------------------------------------

UI repo: https://github.com/ashwanikumarshaw/ReturnOrderManagementSystem-Portal.git

# Development server
Run ng serve for a dev server. Navigate to http://localhost:4200/. The app will automatically reload if you change any of the source files.

## Log in: 
![image](https://user-images.githubusercontent.com/35097948/168912188-c3a42c20-cc7f-470f-8780-1f7d976d7d5e.png)

## Processing  
![image](https://user-images.githubusercontent.com/35097948/168912966-50d42c6b-f2e5-4c99-82fa-7dbf65701de9.png)
![image](https://user-images.githubusercontent.com/35097948/168913023-b9db2846-674d-4e0e-bbfd-61e2ce9f0cef.png)
![image](https://user-images.githubusercontent.com/35097948/168913080-124a7e16-2d0f-429c-83a0-7294c1c48d02.png)

## Processing Charge Paymet Getway
![image](https://user-images.githubusercontent.com/35097948/168913168-e2bbb8d8-63ad-4e4f-87db-a54c87759af1.png)




# Developed by:

Ashwani Kuamr Shaw
