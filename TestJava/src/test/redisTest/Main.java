package test.redisTest;

import java.lang.ref.SoftReference;

public class Main {

	public static void main(String[] args) {
        // TODO Auto-generated method stub
//		new RedisClient().show();
		/*try {
			File file = new File(Class.class.getResource("").getPath());
			System.out.println(file);
			File f = new File("Main.java");
			String path = f.getCanonicalPath() ;
			System.out.println(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		/*try {
			String paramString1 = "我就范德萨+附近的%";
			String paramString2 = "utf-8";
			int i = 0;
			int j = paramString1.length();
			StringBuffer localStringBuffer = new StringBuffer((j > 500) ? j / 2 : j);
			int k = 0;
			if (paramString2.length() == 0)
				throw new UnsupportedEncodingException("URLDecoder: empty string enc parameter");
			byte[] arrayOfByte = null;
			while (k < j) {
				char c = paramString1.charAt(k);
				switch (c) {
				case '+':
					localStringBuffer.append(' ');
					++k;
					i = 1;
					break;
				case '%':
					try {
						if (arrayOfByte == null)
							arrayOfByte = new byte[(j - k) / 3];
						int l = 0;
						while ((k + 2 < j) && (c == '%')) {
							int i1 = Integer.parseInt(paramString1.substring(k + 1, k + 3), 16);
							if (i1 < 0)
								throw new IllegalArgumentException(
										"URLDecoder: Illegal hex characters in escape (%) pattern - negative value");
							arrayOfByte[(l++)] = (byte) i1;
							if ((k += 3) < j)
								c = paramString1.charAt(k);
						}
						if ((k < j) && (c == '%'))
							throw new IllegalArgumentException("URLDecoder: Incomplete trailing escape (%) pattern");
						localStringBuffer.append(new String(arrayOfByte, 0, l, paramString2));
					} catch (NumberFormatException localNumberFormatException) {
						throw new IllegalArgumentException("URLDecoder: Illegal hex characters in escape (%) pattern - "
								+ localNumberFormatException.getMessage());
					}
					i = 1;
					break;
				}
				localStringBuffer.append(c);
				++k;
			}
			System.out.println(((i != 0) ? localStringBuffer.toString() : paramString1));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		int s[] = {99, 50, 15, 16, 43, 48, 27, 68};
		s = quick_sort(s, 0, 7);
		for(int i = 0; i < s.length; i++){
			System.out.println(s[i]);
		}
    }
	static int[] quick_sort(int s[], int l, int r)
	{  
	    if (l < r)
	    {  
	        //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1  
	        int i = l, j = r, x = s[l];  
	        while (i < j)  
	        {  
	            while(i < j && s[j] >= x) // 从右向左找第一个小于x的数  
	                j--;    
	            if(i < j)   
	                s[i++] = s[j];

	            while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
	                i++;
	            if(i < j)
	                s[j--] = s[i];  
	        }  
	        s[i] = x;  
	        quick_sort(s, l, i - 1); // 递归调用   
	        quick_sort
					(s, i + 1, r);
	    } 
	    return s;
	}
}
