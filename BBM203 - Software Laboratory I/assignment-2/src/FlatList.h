//
// Created by muham on 20.11.2022.
//

#ifndef ASS203_2_FLATLIST_H
#define ASS203_2_FLATLIST_H


#include "FlatNode.h"

class FlatList {
    FlatNode * head = nullptr;
public:
    void addFlat(int index, FlatNode* node);

    FlatNode* getHead();
    FlatNode * getFlatNode(int idToFind);
    FlatNode * getFlatNodeByExtracting(int idToFind);

    int getSumOfBandwidths();

    void setHead(FlatNode *node);
    void moveToEnd(FlatNode * newNode);

    std::string toString();

    virtual ~FlatList();
};


#endif //ASS203_2_FLATLIST_H
