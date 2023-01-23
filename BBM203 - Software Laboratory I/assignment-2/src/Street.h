//
// Created by muham on 19.11.2022.
//

#ifndef ASS203_2_STREET_H
#define ASS203_2_STREET_H


#include <string>
#include <vector>
#include "ApartmentList.h"

using namespace std;
class Street {
public:
    ApartmentList* apartmentList = nullptr; // make private
public:
    explicit Street(ApartmentList *apartmentList);

    void addApartmentBefore(const string &newApartmentName, const string& positionName, int maxBandwidth);
    void addApartmentAfter(const string& newApartmentName, const string& positionName, int maxBandwidth);
    void addApartmentHead(const string& newApartmentName, int maxBandwidth);
    void addFlat(const string& apartmentName, int index, int flatId, int initialBandwidth);
    void removeApartment(const string& apartmentName);
    void makeFlatEmpty(const string& apartmentName, int flatId);

    /**
     * get nodes from 'apartment to delete' and put them to the end of the 'apartment to keep' flat list
     * @param apartmentNameToKeep
     * @param apartmentNameToDelete
     */
    void mergeApartments(const string& apartmentNameToKeep, const string& apartmentNameToDelete);
    void relocateFlats(const string& apartmentName, int shiftedFlatId, const vector<int>& addedFlatIds);
};


#endif //ASS203_2_STREET_H
