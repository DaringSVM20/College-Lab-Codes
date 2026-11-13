/*
2. Code for a turn based game, use circular linked lists with player id, name and score.

Delete player data by their player id
Display the score for each player

Use malloc
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct Player{
    int id;
    char name[50];
    int score;
    struct Player* link;
} Player;

Player* createPlayer(int id, char* name) {
    Player* n = (Player*)malloc(sizeof(Player));
    n->id=id;
    strcpy(n->name, name);
    n->score=0;
    n->link=NULL;
    return n;
}

void addPlayer(Player** head, int id, char* name) {
    Player* n = createPlayer(id, name);
    if (*head == NULL) {
        *head = n;
        n->link=*head; // Since it's a circular linked list
    } else {
        Player* temp = *head;
        while (temp->link != *head) {
            temp=temp->link;
        }
        temp->link=n;
        n->link=*head;
    }
}

void deletePlayer(Player** head, int id) {
    if (*head == NULL) {
        printf("Player list is empty.\n");
        return;
    }

    Player* curr = *head;
    Player* prev = NULL;

    while (curr->id != id && curr->link != *head) {
        prev=curr;
        curr=curr->link;
    }
    if (curr->id != id) {
        printf("Player with ID %d not found", id);
        return;
    }

    if (curr == *head && curr->link == *head) {
        free(curr);
        *head = NULL;
        return;
    }

    if (curr == *head) {
        prev = *head;
        while (prev->link != *head) {
            prev=prev->link;
        }
        *head = curr->link;
        prev->link = *head;
        free(curr);
    } else {
        prev->link = curr->link;
        free(curr);
    }
    printf("Player with ID %d deleted successfully.\n", id);
}

void displayScores(Player* head) {
    if (head == NULL) {
        printf("No players to display!\n");
        return;
    }
    Player* temp = head;
    printf("\n--- Player Scores ---\n");
    do {
        printf("Player ID: %d, Name: %s, Score: %d\n", temp->id, temp->name, temp->score);
        temp = temp->link;
    } while (temp != head);
}

int main()
{
    Player* head = NULL;
    int numPlayers, playerId;

    printf("Enter the number of players: ");
    scanf("%d", &numPlayers);
    while (getchar() != '\n');

    for (int i = 0; i < numPlayers; i++) {
        char playerName[50];
        printf("Enter Player %d ID: ", i + 1);
        scanf("%d", &playerId);
        while (getchar() != '\n');
        printf("Enter Player %d Name: ", i + 1);
        fgets(playerName, sizeof(playerName), stdin);
        playerName[strcspn(playerName, "\n")] = 0;
        addPlayer(&head, playerId, playerName);
    }

    // Corrected logic to handle 0 or 1 players
    if (head != NULL) { // Check if at least one player exists
        head->score = 100;
        if (numPlayers > 1) { // Check if a second player exists before accessing head->link
            head->link->score = 200;
        }
    }

    // Example: Delete a player
    printf("\nEnter player ID to delete: ");
    scanf("%d", &playerId);
    deletePlayer(&head, playerId);

    displayScores(head);

    // Free allocated memory
    if (head != NULL) {
        Player* current = head->link;
        while (current != head) {
            Player* temp = current;
            current = current->link;
            free(temp);
        }
        free(head);
    }
    return 0;
}
