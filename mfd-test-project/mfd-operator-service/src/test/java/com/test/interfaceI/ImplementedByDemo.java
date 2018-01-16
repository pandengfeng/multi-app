/**
 * 
 */
package com.test.interfaceI;

import com.google.inject.ImplementedBy;

/**
 * @author pandengfeng
 *
 */
@ImplementedBy(ImplementedByDemoImpl.class)
public interface ImplementedByDemo {
	
	public void test1();
}
