//
// Created by muham on 19.11.2022.
//

#include <iostream>
#include "ApartmentList.h"

ApartmentNode * ApartmentList::getApartment(const string &name) {
    ApartmentNode * node = head;
    while (node) {
        if (node->getName() == name) return node;
        node = node->getNext();
    }
}

ApartmentNode * ApartmentList::getApartmentBefore(const string &name) {
    ApartmentNode * node = head;
    while (node->getNext()) {
        if (node->getNext()->getName() == name) return node;
        node = node->getNext();
    }
}

void ApartmentList::addToStart(ApartmentNode *newNode) {
    // Find the tail:
    ApartmentNode * tail;
    if (head->getNext() != head) { // if there are at least two elements in the list
        ApartmentNode * node = head->getNext();
        while (node->getNext() != head) {
            node = node->getNext();
        }
        // if the next of node is head, then make tail = node
        tail = node;
    }
    else tail = head;
    // --

    newNode->setNext(head);
    head = newNode;
    tail->setNext(head);
}

ApartmentNode * ApartmentList::remove(const string &name) {

    if(head->getName() == name) {
        ApartmentNode * temp = head;
        head = head->getNext();
        delete temp;
        return head; // if there are no more elements head will be null, so function returns null
    }

    ApartmentNode * node = getApartmentBefore(name);
    ApartmentNode * nodeToDelete = node->getNext();
    node->setNext(nodeToDelete->getNext());
    delete nodeToDelete;
    return head;
}

ApartmentNode *ApartmentList::getHead() {
    return head;
}

void ApartmentList::setHead(ApartmentNode *newHead) {
    ApartmentList::head = newHead;
}

FlatNode *ApartmentList::getFlatToRelocate(int flatId) {
    ApartmentNode * node = head;

    do {
        FlatNode * flatNode = node->getFlatList()->getFlatNodeByExtracting(flatId);
        if (flatNode) {
            node->setMaxBandwidth(node->getMaxBandwidth() - flatNode->getBandWidth());
            return flatNode;
        }
        node = node->getNext();
    } while (node != head);

    return nullptr;
}

int ApartmentList::getSumOfMaxBandwidths() {
    int sum=0;
    ApartmentNode * node = head;
    do {
        sum += node->getMaxBandwidth();
        node = node->getNext();
    } while (node != head);
    return sum;
}

ApartmentList::~ApartmentList() {
    if(!head) return;
    ApartmentNode * node = head->getNext();

    while (node != head) {

        ApartmentNode * temp = node;
        node = node->getNext();
        delete temp;
    }

    delete head;
}
