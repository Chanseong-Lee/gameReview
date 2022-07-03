# Project Name
- LCS Critic

## Outline
- The Website where you can leave reviews and ratings for games.
- Users can refer to it when purchasing games, using ratings or reviews.

## Members
- Leader : Chanseong Lee
- Members : Woojin Cho, Hyeokjin Kwon

## Development period
- 1 Months (2022.04 ~ 2022.05)


## Tools
- Spring Tool Suite 3
- SQL Develpoer (Oracle DB)
- MS Code


## Development environment
- Windows 10 64bit
- Oracle DB
- Tomcat 9
- Spring framework 4.3.25
- MyBatis
- Spring Security 4.2.20


## Requirements
- Spring Security-Based Login Authentication, Autorization, and authority Settings per a JSP page.
- Function to send authentication mail to Java MailSender API after signing up for membership.
- Update authority so that only members who have completed the mail authentication can sign in.
- Only the adminstrator can access the admin page and use functions.
- Only the adminstrator can CRUD database of games, items and members.
- "Like" function should be implemented by AJAX.
- there should be a button which can access the admin page and is visible to only the administrator in main view.
- the game list should exist on main view with their agverage rating.
- User can leave review and rating on click of list.
- User can get points if they leave a review.
- User can purchase icons with their points.
- User should have their individual inventory for icons.


## ERD
![image](https://user-images.githubusercontent.com/94969452/177035244-1c2f2b36-8b23-42fa-b073-40548013b481.png)

## Spring Security Context.xml
**3 authority** : ___ROLE_GUEST, ROLE_USER, ROLE_ADMIN___

```
  <security:http auto-config="true" use-expressions="true" once-per-request="false">
      <security:anonymous enabled="true"/>
      <security:csrf disabled="true"/>
      <security:intercept-url pattern="/member/loginForm" access="permitAll" />
      <security:intercept-url pattern="/member/logoutSuccess" access="permitAll" />
      <security:intercept-url pattern="/member/regist" access="permitAll" />
      <security:intercept-url pattern="/member/delete/deleteSuccess" access="permitAll" />
      <security:intercept-url pattern="/member/logout" access="hasAnyRole('ROLE_USER, ROLE_ADMIN')" />
      <security:intercept-url pattern="/member/update/**" access="hasAnyRole('ROLE_USER, ROLE_ADMIN')" />
      <security:intercept-url pattern="/member/delete" access="hasAnyRole('ROLE_USER, ROLE_ADMIN')" />
      <security:intercept-url pattern="/member/**" access="permitAll" />
      <security:intercept-url pattern="/admin/**"   access="hasRole('ROLE_ADMIN')" />
      <security:intercept-url pattern="/game/artdelete/**" access="hasAnyRole('ROLE_USER, ROLE_ADMIN')" />
      <security:intercept-url pattern="/game/artupdate/**" access="hasAnyRole('ROLE_USER, ROLE_ADMIN')" />
      <security:intercept-url pattern="/game/reply/**" access="hasAnyRole('ROLE_USER, ROLE_ADMIN')" />
      <security:intercept-url pattern="/shop/**" access="hasAnyRole('ROLE_USER, ROLE_ADMIN')" />
      <security:intercept-url pattern="/**" access="permitAll" />
      <security:access-denied-handler ref="loginAccessDeniedHandler"/>
      <!-- default-target-url="/member/loginSuccess" -->
      <security:form-login 
         username-parameter="email"
         password-parameter="password"
         login-page="/member/loginForm"
         login-processing-url="/member/login"
         authentication-failure-handler-ref="loginFailureHandler"
         authentication-success-handler-ref="loginSuccessHandler"
      />
      <security:logout
         logout-url="/member/logout"
         logout-success-url="/game/main"
         invalidate-session="false"
         delete-cookies="JSESSIONID,SPRING_SECURITY_REMEMBER_ME_COOKIE"/>
      <security:remember-me key="hsweb" token-validity-seconds="604800" authentication-success-handler-ref="loginSuccessHandler"/>
      <security:session-management invalid-session-url="/member/loginForm"><!-- invalid-session-url : 세션끊겼을때 이동할 페이지 -->
         <security:concurrency-control 
            max-sessions="1" 
            expired-url="/member/loginForm" 
            error-if-maximum-exceeded="false"/>
      </security:session-management>
   </security:http>
```


## Screen shots
- Main (with the slider imgs of top rating game)
![KakaoTalk_20220703_193746552](https://user-images.githubusercontent.com/94969452/177036117-a24a5bea-d5ff-44e6-a789-0abcdebd9ecd.jpg)

- Login
![KakaoTalk_20220703_193747013](https://user-images.githubusercontent.com/94969452/177036135-ab06740e-3d80-41a1-a042-00b955ebb028.jpg)

- Sign up
![KakaoTalk_20220703_193745596](https://user-images.githubusercontent.com/94969452/177036146-ba3a7cce-a41c-4f1a-ac95-64640325c813.jpg)

- Main after login
![KakaoTalk_20220703_193747550](https://user-images.githubusercontent.com/94969452/177036159-4b50793c-8011-4631-8dec-c8e61dff524b.jpg)

- Profile of User
![KakaoTalk_20220703_193801389](https://user-images.githubusercontent.com/94969452/177036167-0a1a68ce-52eb-4df4-83ba-66ce837cb8de.jpg)

- should enter Password for modifying status
![KakaoTalk_20220703_193801911](https://user-images.githubusercontent.com/94969452/177036199-031e16a1-4719-4df0-97c1-0fa0e593f7ff.jpg)

- Be able to update nickname or password
![KakaoTalk_20220703_193802373](https://user-images.githubusercontent.com/94969452/177036221-2474335a-f834-49de-9731-6f799c982ca4.jpg)

![KakaoTalk_20220703_193802847](https://user-images.githubusercontent.com/94969452/177036233-4075076a-a51f-4c38-b421-8ac94ebbea49.jpg)

- Icon shop
![KakaoTalk_20220703_193803335](https://user-images.githubusercontent.com/94969452/177036249-c1c6bba2-d85e-46dc-a654-b0eb3e65c5a8.jpg)

- Inventory
![KakaoTalk_20220703_193803865](https://user-images.githubusercontent.com/94969452/177036254-942c9cd6-66db-4bbe-9435-f9cc33ed5727.jpg)

- Review on a specific game
![KakaoTalk_20220703_193800859](https://user-images.githubusercontent.com/94969452/177036269-9f51b74b-330d-4d1a-b573-f4ff63ee9f21.jpg)

- logout
![KakaoTalk_20220703_193747998](https://user-images.githubusercontent.com/94969452/177036307-b64fa81d-814f-4e38-bbf8-797567de8e99.jpg)

- Admin Dashboard (Only the adminstrator can access here)
![KakaoTalk_20220703_193746023](https://user-images.githubusercontent.com/94969452/177036314-129e2bc5-5f87-4fb3-9a4c-151427765330.jpg)

- Admin Pages

![KakaoTalk_20220703_193800446](https://user-images.githubusercontent.com/94969452/177036413-5c3e4518-fd42-418c-98c1-f67513324708.jpg)
![KakaoTalk_20220703_193804349](https://user-images.githubusercontent.com/94969452/177036414-2d7cd68b-e1e8-4fd0-ac8d-eb6cf244856c.jpg)
![KakaoTalk_20220703_194242965](https://user-images.githubusercontent.com/94969452/177036415-a4ff487f-443b-4415-bc7e-496fce0f9252.jpg)
![KakaoTalk_20220703_194243392](https://user-images.githubusercontent.com/94969452/177036417-17fa147d-5bb2-4477-ab62-05a34e2e125a.jpg)
![KakaoTalk_20220703_193759497](https://user-images.githubusercontent.com/94969452/177036419-5a5e3110-37a4-46dc-8be9-baa1211b026e.jpg)
![KakaoTalk_20220703_193800005](https://user-images.githubusercontent.com/94969452/177036421-194e4d10-fa7a-4ace-a645-8ff3ace36513.jpg)

# Thanks


