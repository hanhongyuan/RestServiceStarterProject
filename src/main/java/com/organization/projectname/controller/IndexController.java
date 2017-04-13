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
package com.organization.projectname.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Muhamad Ridwan <me@muhamadridwan.id>
 */
@RestController
public class IndexController {

    
    //do not require authorization
    @GetMapping(value = {"/", "${base_path}"})
    public Map index() {
        Map map = new HashMap<>();
        map.put("status", "OK");
        map.put("message", "Hello world");
        return map;
    }

    //require authorization
    @GetMapping(value = {"${base_path}/user"})
    public Map user() {
        Map map = new HashMap<>();
        map.put("status", "OK");
        map.put("message", "Hello USER");
        return map;
    }    

    //require authorization Admin Role
    @GetMapping(value = {"${base_path}/admin"})
    public Map admin() {
        Map map = new HashMap<>();
        map.put("status", "OK");
        map.put("message", "Hello ADMIN");
        return map;
    }    
    
}
