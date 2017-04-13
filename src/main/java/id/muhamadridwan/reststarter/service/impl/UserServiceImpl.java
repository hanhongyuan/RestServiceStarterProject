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
package id.muhamadridwan.reststarter.service.impl;

import id.muhamadridwan.reststarter.models.Authority;
import id.muhamadridwan.reststarter.models.User;
import id.muhamadridwan.reststarter.repository.AuthorityRepository;
import id.muhamadridwan.reststarter.repository.UserRepository;
import id.muhamadridwan.reststarter.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Muhamad Ridwan <me@muhamadridwan.id>
 */
@Service
public class UserServiceImpl implements UserService {

    private final Log log = LogFactory.getLog(this.getClass());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(String.format("Load user by username '%s'.", username));
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return user;
        }
    }

    @Override
    public void addUser(User user) throws Exception {
        if (user == null) {
            throw new Exception("Invalid input user");
        } else {
            log.info(String.format("Add User '%s'.", user.getUsername()));
            user.setPassword(passwordEncoder.encode(user.getUsername()));
            userRepository.save(user);
        }
    }

    @Override
    public void addAuthority(Authority authority) throws Exception {
        if (authority == null) {
            throw new Exception("Invalid input authority");
        } else {
            log.info(String.format("Add Authority '%s'.", authority.getAuthority()));
            authorityRepository.save(authority);
        }

    }

}
