package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		if(poly1 == null)
		{
			return poly2;
		}
		else if(poly2 == null)
		{
			return poly1;
		}
		Node answer = new Node(poly1.term.coeff,poly1.term.degree,null);
		Node ptr = poly1;
		Node ptr2 = poly2;
		Node ptr3 = answer;
		
		while(ptr != null && ptr2 != null)
		{
			if(ptr.term.degree == ptr2.term.degree)
			{
			 
			    Node sum = new Node((ptr.term.coeff + ptr2.term.coeff), ptr.term.degree, null);
			    if(sum.term.coeff == 0)
				 {
			    		ptr = ptr.next;
					ptr2 = ptr2.next;
					continue;
				 }
				ptr3.next=sum;
				ptr3=ptr3.next;
				ptr = ptr.next;
				ptr2 = ptr2.next;
			}
			
			else if(ptr.term.degree > ptr2.term.degree)
				{
				 Node sum2 = new Node(ptr2.term.coeff, ptr2.term.degree, null);
				 	ptr3.next=sum2;
					ptr3=ptr3.next;
					ptr2 = ptr2.next;
					
				}
			 else if(ptr.term.degree < ptr2.term.degree)
				{
				 Node sum2 = new Node(ptr.term.coeff, ptr.term.degree, null);
				 	ptr3.next=sum2;
					ptr3=ptr3.next;
					ptr = ptr.next;
				}
			 
			if(ptr != null)
			{
				ptr3.next = ptr;
			}
		else if(ptr2 !=null)
			{
				ptr3.next = ptr2;
			}
				
		}
		
		return answer.next;
			}
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		/** COMPLETE THIS METHOD **/
		if(poly1 == null || poly2 == null )
		{
			return null;
		}
		Node answer = new Node(0,0,null);
		Node ptr = poly1;
		Node ptr2 = poly2;
		Node ptr3 = answer; 
		Node pro2 = null;
		
		while(ptr != null)
		{
			while(ptr2 != null)
			{
				 Node product = new Node(ptr.term.coeff * ptr2.term.coeff, ptr.term.degree + ptr2.term.degree, null);
		
					ptr2 = ptr2.next;
					ptr3 = add(product, ptr3);
			}
			ptr = ptr.next;
			ptr2 = poly2;
		}
		
		for(Node pro = ptr3; pro != null; pro = pro2.next)
		{
			pro2 = new Node(pro.term.coeff,pro.term.degree,pro2);
			if(ptr3.term.coeff == 0 && ptr3.term.coeff == 0)
			{
				return ptr3.next;
			}
		}
		
		return ptr3;
		
		}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		float evaluation = 0;
		
		for(Node ptr = poly; ptr != null; ptr = ptr.next)
		{
			evaluation += ptr.term.coeff * (Math.pow(x, ptr.term.degree));
		}
		return evaluation;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
