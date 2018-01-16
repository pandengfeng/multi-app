/**
 * 
 */
package com.mfd.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pandengfeng
 *
 */
@RestController
public class LocalController {

	 @RequestMapping("/local")
	 public String hello() {
	        return "This is zuul local controller!";
	 }
}
