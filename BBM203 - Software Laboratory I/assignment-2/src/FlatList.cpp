//
// Created by muham on 20.11.2022.
//

#include <iostream>
#include "FlatList.h"

void FlatList::addFlat(int index, FlatNode *node) {
    if (index == 0) {
        if(!head) {
            head = node;
            return;
        }

        node->setNext(head);
        head->setPrev(node);
        head = node;
        return;
    }

    FlatNode * flatNode = head;

    int i = 0;
    while(flatNode) {
        if(i == index-1){ // TODO check if is true.
            flatNode->addAfter(node);
            return;
        }
        i++;
        flatNode = flatNode->getNext();
    }
}

FlatNode *FlatList::getHead() {
    return head;
}

int FlatList::getSumOfBandwidths() {
    int sum = 0;
    for (FlatNode * flatNode = head; flatNode; flatNode = flatNode->getNext())
        sum += flatNode ->getBandWidth();
    return sum;
}

void FlatList::setHead(FlatNode *newHead) {
    head = newHead;
}

FlatNode *FlatList::getFlatNode(int idToFind) {
    FlatNode * flatNode;
    for ( flatNode = head; flatNode; flatNode = flatNode->getNext()) {
        if (flatNode->getId() == idToFind)
            break;
    }
    return flatNode;
}

void FlatList::moveToEnd(FlatNode *newNode) {
    if (!head) {
        head = newNode;
        return;
    }


    FlatNode * flatNode = head;

    while (flatNode->getNext()){
        flatNode = flatNode->getNext();
    }

    flatNode->setNext(newNode);
}

FlatList::~FlatList() {
    FlatNode * node = head;

    while (node) {

        FlatNode * temp = node;
        node = node->getNext();
        delete temp;
    }
}

FlatNode *FlatList::getFlatNodeByExtracting(int idToFind) {
    FlatNode * result = nullptr;
    if (!head) return nullptr;
    if (head->getId() == idToFind) {
        FlatNode * nextFlat = head->getNext();
        if(nextFlat) nextFlat->setPrev(nullptr);
        result = head;
        head->extract();
        head = nextFlat;
    }
    else {
        for ( FlatNode* flatNode = head->getNext(); flatNode; flatNode = flatNode->getNext()) {
            if (flatNode->getId() == idToFind) {
                flatNode->extract();
                result = flatNode;
                break;
            }

        }
    }

    return result;
}

std::string FlatList::toString() {
    if (!head) return "";
    FlatNode * flatNode = head;

    std::string result;
    while (flatNode) {
        result += flatNode->toString();
        flatNode = flatNode->getNext();
        if(flatNode) result += "\t";
    }

    return result;
}
