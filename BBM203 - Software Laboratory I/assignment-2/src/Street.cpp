//
// Created by muham on 19.11.2022.
//

#include "Street.h"
#include "FlatNode.h"

void Street::addApartmentBefore(const string &newApartmentName, const string& positionName, int maxBandwidth) {
    /*
     * there are two cases where the function is called
     * 1) when the first apartment's name is positionName so insert item to begin.
     * 2) the others, so find the apartment before the positionName and add after them.
     */

    auto newApartment = new ApartmentNode(newApartmentName, maxBandwidth);

    if ( apartmentList->getHead()->getName() == positionName)
        apartmentList->addToStart(newApartment);
    else {
        ApartmentNode* apartmentNode = apartmentList->getApartmentBefore(positionName);
        apartmentNode->addAfter(newApartment);
    }
}

void Street::addApartmentAfter(const string& newApartmentName, const string& positionName, int maxBandwidth) {
    auto newApartment = new ApartmentNode(newApartmentName, maxBandwidth);
    ApartmentNode * apartmentNode =  apartmentList->getApartment(positionName);
    apartmentNode->addAfter(newApartment);
}

void Street::addApartmentHead(const string& newApartmentName, int maxBandwidth) {
    // assume that the function is called when the beginning of the program.so we just need to set head.

    auto newApartment = new ApartmentNode(newApartmentName, maxBandwidth);
    apartmentList->setHead(newApartment);

    ApartmentNode * headNode = apartmentList->getHead();
    headNode->setNext(headNode);
}

void Street::addFlat(const string& apartmentName, int index, int flatId, int initialBandwidth) {
    ApartmentNode* apartmentToAddFlat = apartmentList->getApartment(apartmentName);

    // Calculate the flatNodeBandwidth:
    int apartmentMaxBandwidth = apartmentToAddFlat->getMaxBandwidth();
    int sumOfInitialBandwidth = apartmentToAddFlat->getFlatList()->getSumOfBandwidths();
    int flatNodeBandwidth = max(min(apartmentMaxBandwidth - sumOfInitialBandwidth, initialBandwidth), 0);
    // --

    auto newFlat = new FlatNode(flatNodeBandwidth, flatId);
    apartmentToAddFlat->getFlatList()->addFlat(index, newFlat);
}

void Street::removeApartment(const string& apartmentName) {
    apartmentList->remove(apartmentName);
}

void Street::makeFlatEmpty(const string& apartmentName, int flatId) {
    ApartmentNode* apartmentNodeToGetFlat =  apartmentList->getApartment(apartmentName);
    FlatNode* flatNodeToMakeEmpty = apartmentNodeToGetFlat->getFlatList()->getFlatNode(flatId);
    flatNodeToMakeEmpty->setBandWidth(0);
}

void Street::mergeApartments(const string& apartmentNameToKeep, const string& apartmentNameToDelete) {
    ApartmentNode* apartmentToKeep =  apartmentList->getApartment(apartmentNameToKeep);
    ApartmentNode* apartmentToDelete =  apartmentList->getApartment(apartmentNameToDelete);

    FlatNode* flatNodeToCarry = apartmentToDelete->getFlatList()->getHead();

    if(flatNodeToCarry) apartmentToKeep->getFlatList()->moveToEnd(flatNodeToCarry);
    apartmentToKeep->setMaxBandwidth(apartmentToKeep->getMaxBandwidth() + apartmentToDelete->getMaxBandwidth());

    apartmentToDelete->getFlatList()->setHead(nullptr);
    removeApartment(apartmentNameToDelete);

}

void Street::relocateFlats(const string& apartmentName, int shiftedFlatId, const vector<int>& addedFlatIds) {
    auto flatNodeArr = new FlatNode * [addedFlatIds.size()];

    for(int i = 0; i < addedFlatIds.size(); i++) {
        int flatIdToFind = addedFlatIds[i];
        FlatNode * flatNodeToCarry = apartmentList->getFlatToRelocate(flatIdToFind);
        flatNodeArr[i] = flatNodeToCarry;
    }

    ApartmentNode* newApartmentNode = apartmentList->getApartment(apartmentName);
    FlatNode * shiftedFlat = newApartmentNode->getFlatList()->getFlatNode(shiftedFlatId);


    for(int i = 0; i < addedFlatIds.size(); i++) {
        FlatNode * flatNodeToCarry = flatNodeArr[i];
        newApartmentNode->setMaxBandwidth(newApartmentNode->getMaxBandwidth() + flatNodeToCarry->getBandWidth());

        if(shiftedFlat == newApartmentNode->getFlatList()->getHead()) {
            FlatNode * head = newApartmentNode->getFlatList()->getHead();
            flatNodeToCarry->setNext(head);
            head->setPrev(flatNodeToCarry);
            newApartmentNode->getFlatList()->setHead(flatNodeToCarry);
        }
        else {
            shiftedFlat->addBefore(flatNodeToCarry);
        }
    }

    delete[] flatNodeArr;
};

Street::Street(ApartmentList *apartmentList) : apartmentList(apartmentList) {}
