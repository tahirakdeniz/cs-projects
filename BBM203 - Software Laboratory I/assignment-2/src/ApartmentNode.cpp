//
// Created by muham on 19.11.2022.
//

#include <iostream>
#include "ApartmentNode.h"

ApartmentNode::ApartmentNode(const string &name, int maxBandwidth) : name(name), maxBandwidth(maxBandwidth) {
    flatList = new FlatList();
}

ApartmentNode *ApartmentNode::getNext() const {
    return next;
}

void ApartmentNode::setNext(ApartmentNode *newNext) {
    ApartmentNode::next = newNext;
}

FlatList *ApartmentNode::getFlatList() const {
    return flatList;
}

const string &ApartmentNode::getName() const {
    return name;
}

int ApartmentNode::getMaxBandwidth() const {
    return maxBandwidth;
}

void ApartmentNode::addAfter(ApartmentNode *newNode) {
    newNode->next = next;
    next = newNode;
}

void ApartmentNode::setMaxBandwidth(int newMaxBandwidth) {
    ApartmentNode::maxBandwidth = newMaxBandwidth;
}

ApartmentNode::~ApartmentNode() {
    delete flatList;
}

