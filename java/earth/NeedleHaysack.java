/* IMPORTANT: class must not be public. */


 //* uncomment this if you want to read input.
import java.io.BufferedReader;
import java.io.InputStreamReader;


class NeedleHaysack {
    public static void main(String args[] ) throws Exception {
        
         //* Read input from stdin and provide input before running

       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int T = Integer.parseInt(line);
        while(T!=0)
		 {
				String text = null;
				String pattern = null;
				text = br.readLine();
				pattern = br.readLine();
				int text_len = text.length();
				int pattern_length = pattern.length();
				int count_t[] = new int[26];
				int count_p[] = new int[26];
				boolean Isthere = false;
				for(int i =0;i<26;i++)
				{
					count_t[i] = 0;
					count_p[i] = 0;
				}
				for(int j =0;j<text_len;j++)
				{
					count_t[(int)text.charAt(j)-'a']++;
					count_p[(int)pattern.charAt(j)-'a']++;
				}
				
				for(int k = text_len;k<pattern_length;k++)
				{
					int found = 1;
					for(int l=0;l<26;l++)
					{
						if(count_t[k]!=count_p[k])
						{
							found =0;
							break;
						}
					}
					if(found==1)
					{
						Isthere = true;
						break;
					}
					count_p[(int)pattern.charAt(k-text_len)-'a']--;
					count_p[(int)pattern.charAt(k)-'a']++;
				}
				int found = 1;
				for(int k=0;k<26 && !Isthere;k++)
				{
					if(count_t[k] != count_p[k])
					{
						found = 0;break;
					}
				}
            if(found==1) Isthere=true;
				if(Isthere)
					System.out.println("YES");
				else System.out.println("NO");
		 T--;
		 }
}
}
