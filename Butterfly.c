#include <stdio.h>
#include <stdlib.h>

int main()
{
    int i,j;

    for(i=1;i<=5;i++)
    {
        for(j=1;j<=i;j++)
        {
            printf("*");
        }
        for(j=1;j<=5-i;j++)
        {
            printf(" ");
        }
        for(j=1;j<=5-i;j++)
        {
            printf(" ");
        }
        for(j=1;j<=i;j++)
        {
            printf("*");
        }
        printf("\n");
    }
    for(i=1;i<=5;i++)
    {
        for(j=5;j>=i;j--)
        {
            printf("*");
        }
        for(j=5;j>6-i;j--)
        {
            printf(" ");
        }
        for(j=5;j>6-i;j--)
        {
            printf(" ");
        }
        for(j=5;j>=i;j--)
        {
            printf("*");
        }
        printf("\n");

    }

}
