import java.nio.channels.UnsupportedAddressTypeException;
import java.util.Iterator;

import javax.swing.text.StyledEditorKit.ForegroundAction;
///////���õ�����������ģʽ
interface Generator<T>{T next();}

class Fibonacci implements Generator<Integer>{
	private int count=0;
	public Integer next(){
		return flib(count++);
	}
	private int flib(int n){
		if(n<2)
			return 1;
		return flib(n-2)+flib(n-1);
	}
}
////����������ģʽʵ��
/*
class IterableFibonacci extends Fibonacci implements Iterable<Integer>{
	private int n;
	public IterableFibonacci(int n){
		this.n=n;
	}
	public Iterator<Integer> iterator(){
		return new Iterator<Integer>() {
			public boolean hasNext(){return n>0;}
			public Integer next(){
				n--;
				return IterableFibonacci.this.next();////�����Ҫ���ɶ��ⲿ���������ã�����ʹ���ⲿ������ֺ������Բ���this
				
			}
			public void remove(){
				throw new UnsupportedOperationException();
			}
		};
	}
}*/
////����������ģʽ
class IterableFibonacci implements Iterable<Integer>{
	private int n;
	private Fibonacci fibonacci;
	public IterableFibonacci(Fibonacci fibonacci,int n){
		this.fibonacci=fibonacci;
		this.n=n;
	}
	public Iterator<Integer> iterator(){
		return new Iterator<Integer>() {
			public boolean hasNext(){
				return n>0;
			}
			public Integer next(){
				n--;
				return fibonacci.next();
			}
			public void remove(){
				throw new UnsupportedOperationException();
			}
		};
	}
}
public class adapter{
	public static void main(String[] args){
		//Fibonacci gen=new Fibonacci();
		
		//for(int i=0;i<18;i++){
		//	System.out.println(gen.next()+" ");
		//}
		for(int i:new IterableFibonacci(new Fibonacci(),18))
			System.out.println(i);
			
	}	
}