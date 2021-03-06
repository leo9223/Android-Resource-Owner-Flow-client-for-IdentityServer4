##Android Resource Owner Flow Client for IdentityServer4

[Assumptions](README.md#assumptions) | [Functionality](README.md#functionality) | [How to run?](README.md#how-to-run) | [Contribution](README.md#contribution) | [Credits](README.md#credits) | [Screen Shots](README.md#screen-shots)

###Assumptions
1. You have got AuthorizationServer(idSvrHost) and ResourceServer(SampleApi) from the 
official repo
https://github.com/IdentityServer/IdentityServer4.Samples
https://github.com/IdentityServer/IdentityServer4.Samples/tree/dev/Mvc/src

2. You have published AuthorizationServer and working fine (I used IIS 10)
3.
2. You have published ResourceServer and working fine (I used IIS 10)

3. You have Android Studio (I used Android Studio 1.5)

4. I have used Android Emulator to test this App

###Functionality
1. This App is based on Resource Owner Flow

2. It is Accessing Token (from AuthorizationServer)

3. It is Refreshing Token (from AuthorizationServer)

4. It is Calling Service (from ResourceServer)

###How to run?
1. Load project in Android Studio

2. set your deployed IP of AuthorizationServer and ResourceServer in "yourpath...\IDSvr4AndroidClient\app\src\main\java\abubakar\IDSvr4ROClientDroid\constants.java"

3. run or debug app in emulator


###Contribution
You are warmly welcome to help me in making this code more better and adding more functionality of IdentityServer4. Please ping me at @leo9223

abubakar.ikram@outlook.com

Muhammad Abubakar

###Credits
I would really like to say thanks to [Dominick Baier](https://github.com/leastprivilege) and [Brock Allen](https://github.com/brockallen) they really help me to complete this.



###Screen Shots


####Startup screen
![Alt text](img1.png?raw=true "Startup screen")


####Access Token
![Alt text](img2.png?raw=true "Access Token")


####Refresh Token
![Alt text](img3.png?raw=true "Refresh Token")


####Calling Service
![Alt text](img4.png?raw=true "Calling Service")
