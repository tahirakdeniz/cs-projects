//
// Created by muham on 19.11.2022.
//

#ifndef ASS203_2_APARTMENTNODE_H
#define ASS203_2_APARTMENTNODE_H


#include <string>
#include "FlatList.h"

using namespace std;

class ApartmentNode {
private:
    ApartmentNode *next = nullptr;
    FlatList *flatList = nullptr;
    string name;
    int maxBandwidth;
public:
    ApartmentNode(const string &name, int maxBandwidth);

    ApartmentNode *getNext() const;

    void setNext(ApartmentNode *newNext);

    FlatList *getFlatList() const;

    const string &getName() const;

    int getMaxBandwidth() const;

    /**
     *
     * @param newNode new node to be added after this node
     */
    void addAfter(ApartmentNode *newNode);

    void setMaxBandwidth(int newMaxBandwidth);

    virtual ~ApartmentNode();

};


#endif //ASS203_2_APARTMENTNODE_H
