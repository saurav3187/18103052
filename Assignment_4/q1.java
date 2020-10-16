import java.util.Scanner;
import java.util.*;

class q1
{
    public static void main(String[] args)
    {
        int n, m;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        in.nextLine();
        String temp;
        String[] crops = new String[n];
        for (int i=0;i<n;i++)
        {
            crops[i]=in.nextLine().trim();
        }
        char[][] crops_mat = new char[n][m];
        for(int i=0; i<n; i++)
        {
            for(int j=0;j<m; j++)
            {
                crops_mat[i][j] = crops[i].charAt(j);
            }
        }
        char[] crop_types = new char[26];
        for(char c='a'; c<'z'; c++)
        {
            crop_types[c-'a'] = c;
        }

        System.out.println(replant(crops_mat, 0, 0, n, m, 0, n*m, crop_types));
    }

    public static int replant(char[][] crops_mat, int curr_row, int curr_col, int n, int m, int curr_count, int minm_count, char[] crop_types)
    {
        Set<Character> left_up = new HashSet<Character>();
        if(curr_row!=0)
        {
            left_up.add(crops_mat[curr_row-1][curr_col]);
        }
        if(curr_col!=0)
        {
            left_up.add(crops_mat[curr_row][curr_col-1]);
        }
        Set<Character> right_down = new HashSet<Character>();
        if(curr_row!=n-1)
        {
            right_down.add(crops_mat[curr_row+1][curr_col]);
        }
        if(curr_col!=m-1)
        {
            right_down.add(crops_mat[curr_row][curr_col+1]);
        }
        boolean present_left_up = false;
        if(left_up.contains(crops_mat[curr_row][curr_col]))
        {
            present_left_up = true;
        }
        char[][] new_crops_mat = new char[n][m];
        if(right_down.contains(crops_mat[curr_row][curr_col]))
        {
            if(present_left_up)
            {
                for(int k=0; k<26; k++)
                {
                    if(!left_up.contains(crop_types[k]) && !right_down.contains(crop_types[k]))
                    {
                        for(int i=0; i<n; i++)
                        {
                            for(int j=0;j<m;j++)
                            {
                                new_crops_mat[i][j] = crops_mat[i][j];
                            }
                        }
                        new_crops_mat[curr_row][curr_col] = crop_types[k];
                        if(curr_col<m-1)
                        {
                            minm_count = Math.min(minm_count, replant(new_crops_mat, curr_row, curr_col+1, n, m, curr_count+1, minm_count, crop_types));
                        }
                        else if(curr_row<n-1)
                        {
                            minm_count = Math.min(minm_count, replant(new_crops_mat, curr_row+1, 0, n, m, curr_count+1, minm_count, crop_types));
                        }
                        else
                        {
                            return Math.min(curr_count+1, minm_count);
                        }
                        break;
                    }
                }
            }
            else
            {
                if(curr_col<m-1)
                {
                    minm_count = Math.min(minm_count, replant(crops_mat, curr_row, curr_col+1, n, m, curr_count, minm_count, crop_types));
                }
                else if(curr_row<n-1)
                {
                    minm_count = Math.min(minm_count, replant(crops_mat, curr_row+1, 0, n, m, curr_count, minm_count, crop_types));
                }
                else
                {
                    return Math.min(curr_count, minm_count);
                }
                for(int k=0; k<26;k++)
                {
                    if(!left_up.contains(crop_types[k]) && !right_down.contains(crop_types[k]))
                    {
                        for(int i=0; i<n; i++)
                        {
                            for(int j=0; j<m; j++)
                            {
                                new_crops_mat[i][j] = crops_mat[i][j];
                            }
                        }
                        new_crops_mat[curr_row][curr_col] = crop_types[k];
                        if(curr_col<m-1)
                        {
                            minm_count = Math.min(minm_count, replant(new_crops_mat, curr_row, curr_col+1, n, m, curr_count+1, minm_count, crop_types));
                        }
                        else if(curr_row<n-1)
                        {
                            minm_count = Math.min(minm_count, replant(new_crops_mat, curr_row+1, 0, n, m, curr_count+1, minm_count, crop_types));
                        }
                        else
                        {
                            return Math.min(curr_count+1, minm_count);
                        }
                        break;
                    }
                }

            }
        }
        else
        {
            if(present_left_up)
            {
                for(int k=0; k<26;k++)
                {
                    if(!left_up.contains(crop_types[k]) && !right_down.contains(crop_types[k]))
                    {
                        for(int i=0; i<n; i++)
                        {
                            for(int j=0;j<m;j++)
                            {
                                new_crops_mat[i][j] = crops_mat[i][j];
                            }
                        }
                        new_crops_mat[curr_row][curr_col] = crop_types[k];
                        if(curr_col<m-1)
                        {
                            minm_count = Math.min(minm_count, replant(new_crops_mat, curr_row, curr_col+1, n, m, curr_count+1, minm_count, crop_types));
                        }
                        else if(curr_row<n-1)
                        {
                            minm_count = Math.min(minm_count, replant(new_crops_mat, curr_row+1, 0, n, m, curr_count+1, minm_count, crop_types));
                        }
                        else
                        {
                            return Math.min(curr_count+1, minm_count);
                        }
                        break;
                    }
                }
            }
            else
            {
                if(curr_col<m-1)
                {
                    minm_count = Math.min(minm_count, replant(crops_mat, curr_row, curr_col+1, n, m, curr_count, minm_count, crop_types));
                }
                else if(curr_row<n-1)
                {
                    minm_count = Math.min(minm_count, replant(crops_mat, curr_row+1, 0, n, m, curr_count, minm_count, crop_types));
                }
                else
                {
                    return Math.min(curr_count, minm_count);
                }
            }
        }
        return minm_count;
    }
}