//
// Created by muham on 19.11.2022.
//

#include <iostream>
#include "FlatNode.h"

FlatNode::FlatNode(int initialBandWidth, int id) : bandWidth(initialBandWidth), id(id) {
    isEmpty = bandWidth == 0;
}

void FlatNode::addAfter(FlatNode *node) {
    // Assume that node is not null
    //node->extract();

    FlatNode * nextNode = this->getNext();
    next = node;
    node->prev = this;
    node->next = nextNode;
    if (nextNode) nextNode->prev = node;
}

void FlatNode::addBefore(FlatNode *node) {
    // Assume that node is not null

    FlatNode * prevNode = prev;
    prev = node;
    node->next = this;
    node->prev = prevNode;
    if (prevNode) prevNode->next = node;
}

int FlatNode::getBandWidth() const {
    return bandWidth;
}

void FlatNode::setBandWidth(int newBandWidth) {
    bandWidth = newBandWidth;
    isEmpty = bandWidth == 0;
}

FlatNode *FlatNode::getNext() const {
    return next;
}

void FlatNode::setNext(FlatNode *newNext) {
    FlatNode::next = newNext;
}

FlatNode *FlatNode::getPrev() const {
    return prev;
}

void FlatNode::setPrev(FlatNode *newPrev) {
    FlatNode::prev = newPrev;
}

int FlatNode::getId() const {
    return id;
}

void FlatNode::extract() {
    if(prev) {
        prev->next = next;
        prev = nullptr;
    }
    if(next) {
        next->prev = prev;
        next = nullptr;
    }
}

std::string FlatNode::toString() const {
    std::string result;
    result +="Flat";
    result += std::to_string(id);
    result += '(';
    result += std::to_string(bandWidth);
    result += ')';
    return result;
}

bool FlatNode::getIsEmpty() const {
    return isEmpty;
}
