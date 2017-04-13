## Rest Service Starter Project ## 
Rest Api Starter project based on Spring boot (Security with JWT, Spring Security)

### How To Test ###
Use [POSTMAN](https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop) :  
* Get Token,  POST :  http://127.0.0.1:8080/api/v1/auth with data : 

  ```json
  {
     "username" : "test",
     "password" : "test"
  }
  ```
  OR 
  
  ```json
  {
     "username" : "ridwan",
     "password" : "ridwan"
  }
  ```
  
  You will get response : 
    ```json
    {
  		"token": "eyTOKENblablabla"
  	}
    ```
 
 
 * Navigate to : http://127.0.0.1:8080/api/v1/admin with header request : 
 	```
    Authorization : TOKEN_HERE
    ```
    You will get response : 
    ```json
    {
        "message": "Hello ADMIN",
        "status": "OK"
    }
  	```  
  
### Lisence ###
  * [MIT LISENCE](http://www.opensource.org/licenses/mit-license.php)
