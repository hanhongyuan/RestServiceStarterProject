/* 
 * The MIT License
 *
 * Copyright 2017 Muhamad Ridwan - muhamadridwan.id.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.organization.projectname.bootload;

import com.organization.projectname.models.Authority;
import com.organization.projectname.models.IPWhitelist;
import com.organization.projectname.models.User;
import com.organization.projectname.service.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author Muhamad Ridwan <me@muhamadridwan.id>
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent e) {
        log.info("Starting data loading...!");
        
        Authority roleAdmin = new Authority("ROLE_ADMIN");
        Authority roleUser = new Authority("ROLE_USER");

        User adminUser = new User("ridwan", "Muhamad", "Ridwan", "me@muhamadridwan.id");
        adminUser.setAuthority(roleAdmin);
        
        User testUser = new User("test", "testFirstname", "testLastName", "test@gmail.com");
        testUser.setAuthority(roleUser);
        
        IPWhitelist ipw = new IPWhitelist();
        ipw.setIpAddr("127.0.0.1");
        

        try {
            userService.addAuthority(roleAdmin);
            userService.addAuthority(roleUser);
            userService.addUser(adminUser);
            userService.addUser(testUser);
            userService.addIpWhiteList(ipw);
        } catch (Exception ex) {
            Logger.getLogger(DataLoader.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
