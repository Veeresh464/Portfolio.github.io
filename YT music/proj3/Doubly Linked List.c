#include <stdio.h>
#define null 0
struct s
{
    char n[15];
    char t[15];
    float d;
    struct s *linkf;
    struct s *linkb;
};
main()
{
    struct s *head, *p;
    int ch;
    head = null;
    struct s *insert_at_front(struct s * head, struct s * p);
    struct s *insert_at_end(struct s * head, struct s * p);
    struct s *del_at_front(struct s * head);
    struct s *del_at_end(struct s * head);
    void disp(struct s * head);
    void dispr(struct s * head);
    int counti(struct s * head);
    struct s *insert_at_anypos(struct s * head, struct s * p, int n);
    struct s *del_at_anypos(struct s * head, int n);
    struct s *search_by_name(struct s * head);

    do
    {
        printf("1--Insert at front\n");
        printf("2--Insert at end\n");
        printf("3--Delete at front\n");
        printf("4--Delete at end\n");
        printf("5--Display\n");
        printf("6--Display in reverse\n");
        printf("7--Count the total number of songs\n");
        printf("8--Insert at any position\n");
        printf("9--Del at any pos\n");
        printf("10--Search by name\n");
        printf("11--Insert after the given position\n");
        printf("12--Insert before the given position\n");
        printf("13--Insert after the given name\n");
        printf("14--Insert before the given name\n");
        printf("15--Sort the linked list on the time duration\n");
        printf("16--Exit\n");

        printf("Choose the option\n");
        scanf("%d", &ch);

        switch (ch)
        {

        case 1:
            p = (struct s *)malloc(sizeof(struct s));
            printf("Enter the Singer Name,Title,Duration of song\n");
            scanf("%s%s%f", &p->n, &p->t, &p->d);
            if (head == null)
            {
                head = p;
                p->linkf = null;
                p->linkb = null;
                break;
            }
            else
            {
                head = insert_at_front(head, p);
                break;
            }
        case 2:
            p = (struct s *)malloc(sizeof(struct s));
            printf("Enter the Singer Name,Title,Duration of song\n");
            scanf("%s%s%f", &p->n, &p->t, &p->d);
            head = insert_at_end(head, p);
            break;
        case 3:
            if (head == null)
            {
                printf("Linked list is empty\n");
                break;
            }
            else
            {
                head = del_at_front(head);
                break;
            }
        case 4:
            if (head == null)
            {
                printf("The linked list is empty\n");
                break;
            }
            else
            {
                head = del_at_end(head);
                break;
            }
        case 5:
            if (head == null)
            {
                printf("The linked list empty\n");
                break;
            }
            else
            {
                disp(head);
                break;
            }
        case 6:
            if (head == null)
            {
                printf("The linked list empty\n");
                break;
            }
            else
            {
                dispr(head);
                break;
            }
        case 7:
            if (head == null)
            {
                printf("Nodes=0\n");
                break;
            }
            else
            {
                int ans;
                ans = counti(head);
                break;
            }
        case 8:
            p = (struct s *)malloc(sizeof(struct s));
            printf("Enter the Singer Name,Title,Duration of song\n");
            scanf("%s%s%f", &p->n, &p->t, &p->d);
            int pos;
            printf("Enter pos\n");
            scanf("%d", &pos);
            if (pos == 1)
            {
                p->linkf = head;
                p->linkb = null;
                head->linkb = p;
                head = p;
                break;
            }
            else if (pos == counti(head) + 1)
            {
                head = insert_at_end(head, p);
                break;
            }
            else
            {
                head = insert_at_anypos(head, p, pos);
                break;
            }
        case 9:
            printf("Enter the pos\n");
            int po;
            printf("Enter the pos\n");
            scanf("%d", &po);
            if (po == 1)
            {
                head = del_at_front(head);
                break;
            }
            else if (po == counti(head) + 1)
            {
                printf("Invalid entry\n");
                break;
            }
            else
            {
                head = del_at_anypos(head, po);
                break;
            }
        case 10:
            if (head == null)
            {
                printf("The linked list is empty\n");
                break;
            }
            else
            {
                head = search_by_name(head);
                break;
            }
        }
    } while (ch != 15);
}

struct s *insert_at_front(struct s *head, struct s *p)
{
    struct s *curr = head;
    head = p;
    p->linkf = curr;
    p->linkb = null;
    curr->linkb = p;
    return head;
};

struct s *insert_at_end(struct s *head, struct s *p)
{
    if (head == null)
    {
        head = p;
        p->linkf = null;
        p->linkb = null;
        return head;
    }
    else
    {
        struct s *temp;
        temp = head;
        while (temp->linkf != null)
        {
            temp = temp->linkf;
        }
        temp->linkf = p;
        p->linkb = temp;
        p->linkf = null;
        return head;
    }
};

struct s *del_at_front(struct s *head)
{
    if (head->linkf == null)
    {
        printf("Deleting node is\n");
        printf("N=%s  T=%s  Dur=%f\n", head->n, head->t, head->d);
        free(head);
        head = null;
        return head;
    }
    else
    {
        struct s *temp;
        temp = head->linkf;
        printf("Deleting node is\n");
        printf("N=%s  T=%s  Dur=%f\n", head->n, head->t, head->d);
        free(head);
        head = temp;
        temp->linkb = null;
        return head;
    }
};

struct s *del_at_end(struct s *head)
{
    struct s *start, *curr;
    if (head->linkf == null)
    {
        printf("Deleting node is\n");
        printf("N=%s  T=%s  Dur=%f\n", head->n, head->t, head->d);
        free(head);
        head = null;
        return head;
    }
    else
    {
        start = head;
        while (start->linkf != null)
        {
            curr = start;
            start = start->linkf;
        }
        printf("Deleting node is\n");
        printf("N=%s  T=%s  Dur=%f\n", start->n, start->t, start->d);
        free(start);
        curr->linkf = null;
        return head;
    }
}

void disp(struct s *head)
{
    struct s *start = head;
    while (start->linkf != null)
    {
        printf("N=%s  T=%s  Dur=%f\n", start->n, start->t, start->d);
        start = start->linkf;
    }
    printf("N=%s  T=%s  Dur=%f\n", start->n, start->t, start->d);
}

void dispr(struct s *head)
{
    struct s *temp;
    while (temp->linkf != null)
    {
        temp = temp->linkf;
    }
    while (temp->linkb != null)
    {
        printf("N=%s  T=%s  Dur=%f\n", temp->n, temp->t, temp->d);
        temp = temp->linkb;
    }
    printf("N=%s  T=%s  Dur=%f\n", temp->n, temp->t, temp->d);
}

int counti(struct s *head)
{
    int count = 1;
    struct s *start = head;
    while (start->linkf != null)
    {
        start = start->linkf;
        count++;
    }
    printf("Total number of songs=%d\n", count);
    return count;
}

struct s *insert_at_anypos(struct s *head, struct s *p, int pos)
{
    int t = 1;
    struct s *start, *next;
    start = head;
    while (t < pos - 1)
    {
        start = start->linkf;
        t++;
    }
    next = start->linkf;
    start->linkf = p;
    p->linkb = start;
    p->linkf = next;
    next->linkb = p;
    return head;
};

struct s *del_at_anypos(struct s *head, int pos)
{
    int t = 1;
    struct s *start, *next, *pre;
    start = head;
    while (t < pos)
    {
        pre = start;
        start = start->linkf;
        next = start->linkf;
        t++;
    }
    printf("Deleting node is\n");
    printf("N=%s  T=%s  Dur=%f\n", start->n, start->t, start->d);
    free(start);
    pre->linkf = next;
    next->linkb = pre;
    return head;
};

struct s *search_by_name(struct s *head)
{
    char str[15];
    printf("Enter the name\n");
    fflush(stdin);
    gets(str);
    printf("Entered name is :");
    puts(str);
    struct s *start = head;
    int count = 0;
    while (start->linkf != head)
    {
        if (strcmp((start)->n, str) == 0)
        {
            printf("N=%s  T=%s  Dur=%f\n", start->n, start->t, start->d);
            count++;
        }
        start = start->linkf;
    }
    if (count == 0)
    {
        printf("Not found\n");
    }
    return head;
}
