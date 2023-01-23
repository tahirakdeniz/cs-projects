//
// Created by muham on 19.11.2022.
//

#ifndef ASS203_2_APARTMENTLIST_H
#define ASS203_2_APARTMENTLIST_H

#include <string>
#include "ApartmentNode.h"

class ApartmentList {
private:
    ApartmentNode *head = nullptr;
public:
    /**
     * assumes there exists the node with the given name
     * searches an apartment node in the list
     * @param name name of the node to search
     * @return apartment node that has the given name
     */
    ApartmentNode * getApartment(const string &name);

    /**
     * assumes there exists the node with the given name
     * searches an apartment node in the list
     * @param name name of the node to search
     * @return apartment node that is in the front of the node with the given name
     */
    ApartmentNode * getApartmentBefore(const string &name);

    ApartmentNode * getHead();

    /**
     *
     * @param newNode new node to add to start of list
     */
    void addToStart(ApartmentNode *newNode);

    /**
     * removes and frees the node from the list by the given name
     * also it frees flatList at the same time when the node is removeing (int destructor of the ApartmentNode)
     * @param name node name to delete and free
     * @return head of the list
     */
    ApartmentNode * remove(const string &name);

    void setHead(ApartmentNode *newHead);

    /**
     * finds the node with the given id by searching every apartment in the list.
     * @param flatId flat id to find
     * @return flat node that has the given id.
     */
    FlatNode* getFlatToRelocate(int flatId);

    int getSumOfMaxBandwidths();

    virtual ~ApartmentList();
};


#endif //ASS203_2_APARTMENTLIST_H
