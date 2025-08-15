#include <bits/stdc++.h>
using namespace std;

struct Node {
    Node* next[26];  // 26 lowercase alphabets
    int count;

    Node() {
        count = 0;
        for (int i = 0; i < 26; i++)
            next[i] = NULL;
    }
};

void addWord(Node* root, string& word) {
    Node* temp = root;
    for (char ch : word) {
        int idx = ch - 'a';
        if (temp->next[idx] == NULL)
            temp->next[idx] = new Node();
        temp = temp->next[idx];
    }
    temp->count++;  // mark end of word
}

bool findWord(Node* root, string& word) {
    Node* temp = root;
    for (char ch : word) {
        int idx = ch - 'a';
        if (temp->next[idx] == NULL)
            return false;
        temp = temp->next[idx];
    }
    return temp->count > 0;
}

bool removeWord(Node* root, string& word) {
    Node* curr = root;
    Node* prevSplit = NULL;
    char splitChar = 'a';

    for (char ch : word) {
        int idx = ch - 'a';
        if (curr->next[idx] == NULL)
            return false;

        int childCount = 0;
        for (int i = 0; i < 26; i++)
            if (curr->next[i] != NULL)
                childCount++;

        if (childCount > 1) {
            prevSplit = curr;
            splitChar = ch;
        }

        curr = curr->next[idx];
    }

    // check if the last node has children
    int check = 0;
    for (int i = 0; i < 26; i++)
        if (curr->next[i] != NULL)
            check++;

    if (check > 0) {
        curr->count--;
        return true;
    }

    if (prevSplit != NULL) {
        prevSplit->next[splitChar - 'a'] = NULL;
        return true;
    } else {
        root->next[word[0] - 'a'] = NULL;
        return true;
    }
}

int main() {
    Node* root = new Node();

    vector<string> words = {"and", "ant", "do", "geek", "dad", "ball"};
    for (auto& w : words)
        addWord(root, w);

    vector<string> lookups = {"do", "geek", "bat"};
    for (auto& q : lookups) {
        cout << "Looking for: " << q << "\n";
        if (findWord(root, q))
            cout << "Yes, it's there!\n";
        else
            cout << "Nope, not found.\n";
    }

    vector<string> deletions = {"geek", "tea"};
    for (auto& d : deletions) {
        cout << "Trying to remove: " << d << "\n";
        if (removeWord(root, d))
            cout << "Deleted if existed.\n";
        else
            cout << "Not found to delete.\n";
    }

    return 0;
}
