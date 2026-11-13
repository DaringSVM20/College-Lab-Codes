/*
1. You are a tourist company, create a struct with city name and number of visitors

Display the total number of tourists
Display city name and its tourists

Use dynamic memory allocation
*/

#include <stdio.h>
#include <stdlib.h>

typedef struct City {
    char name[50];
    int visitors;
    struct City* link;
} City;

City* createCity(char* name, int visitors) {
    City* n = (City*)malloc(sizeof(City));
    if (n == NULL) {
        printf("Memory allocation failed");
        exit(1);
    }
    strcpy(n->name, name);
    n->visitors=visitors;
    n->link=NULL;
    return n;
}

void displayCities(City* head) {
    City* curr = head;
    if (curr == NULL) {
        printf("No city data to display.\n");
        return;
    }
    printf("\nCity Visitors:\n");
    while (curr != NULL) {
        printf("City: %s, Visitors: %d\n", curr->name, curr->visitors);
        curr=curr->link;
    }
}

void displayTotalVisitors(City* head) {
    City* curr = head;
    int totalVisitors = 0;
    while (curr != NULL) {
        totalVisitors += curr->visitors;
        curr = curr->link;
    }
    printf("\nTotal number of tourists: %d\n", totalVisitors);
}

int main()
{
    City* head = NULL;
    City* curr = NULL;
    int numCities;

    printf("Enter number of cities: ");
    scanf("%d", &numCities);
    getchar();

    for (int i=0; i<numCities; i++) {
        char cityName[50];
        int visitorCount;

        printf("Enter name of City %d: ", i+1);
        fgets(cityName, sizeof(cityName), stdin);
        cityName[strcspn(cityName, "\n")] = 0;

        printf("Enter number of visitors for %s: ", cityName);
        scanf("%d", &visitorCount);
        getchar();

        City* n = createCity(cityName, visitorCount);

        if (head == NULL) {
            head = n;
            curr = head;
        } else {
            curr->link=n;
            curr=n;
        }
    }

    displayCities(head);
    displayTotalVisitors(head);

    curr = head;
    while(curr != NULL) {
        City* temp = curr;
        curr = curr->link;
        free(temp);
    }

    return 0;
}
