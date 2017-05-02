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
package com.organization.projectname.service.impl;

import com.organization.projectname.config.SecurityUtil;
import com.organization.projectname.exception.IPBlockedException;
import com.organization.projectname.exception.InvalidInputException;
import com.organization.projectname.models.Authority;
import com.organization.projectname.models.IPWhitelist;
import com.organization.projectname.models.User;
import com.organization.projectname.repository.AuthorityRepository;
import com.organization.projectname.repository.IPWhitelistRepository;
import com.organization.projectname.repository.UserRepository;
import com.organization.projectname.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
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

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private IPWhitelistRepository iPWhitelistRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info(String.format("Load user by username '%s'.", username));
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid credential");
        } else {
            return user;
        }
    }

    @Override
    public void addUser(User user) throws InvalidInputException {
        if (user == null) {
            throw new InvalidInputException("Invalid input user");
        } else {
            log.info(String.format("Add User '%s'.", user.getUsername()));
            user.setPassword(passwordEncoder.encode(user.getUsername()));
            userRepository.save(user);
        }
    }

    @Override
    public void addAuthority(Authority authority) throws InvalidInputException {
        if (authority == null) {
            throw new InvalidInputException("Invalid input authority");
        } else {
            log.info(String.format("Add Authority '%s'.", authority.getAuthority()));
            authorityRepository.save(authority);
        }

    }

    @Override
    public void isIPOK() throws AuthenticationException {

        String ip = SecurityUtil.getClientIP(request);
        log.info(String.format("Load Ip '%s'.", ip));

        IPWhitelist ipw = iPWhitelistRepository.findByIpAddr(ip);

        if (ipw == null) {
            throw new IPBlockedException("Invalid IP");
        }

    }

    @Override
    public void addIpWhiteList(IPWhitelist iPWhitelist) throws InvalidInputException {
        if (iPWhitelist == null) {
            throw new InvalidInputException("Invalid input ip");
        } else {
            log.info(String.format("Add whitelist ip '%s'.", iPWhitelist.getIpAddr()));            
            iPWhitelistRepository.save(iPWhitelist);
        }

    }

}
