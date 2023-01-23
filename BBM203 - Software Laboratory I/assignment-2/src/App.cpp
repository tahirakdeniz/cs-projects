//
// Created by muham on 21.11.2022.
//

#include <sstream>
#include "App.h"

#define ADD_APARTMENT "add_apartment"
#define ADD_FLAT "add_flat"
#define REMOVE_APARTMENT "remove_apartment"
#define MAKE_FLAT_EMPTY "make_flat_empty"
#define FIND_SUM_OF_MAX_BANDWIDTH "find_sum_of_max_bandwidths"
#define MERGE_TWO_APARTMENTS "merge_two_apartments"
#define RELOCATE_FLATS_TO_SAME_APARTMENT "relocate_flats_to_same_apartment"
#define LIST_APARTMENTS "list_apartments"
#define HEAD "head"
#define BEFORE "before"
#define AFTER "after"
#define COMMA ','

App::App(Street *street, const string &inputFileName, Writer *writer) : street(street), writer(writer){
    auto stream = new fstream();
    stream->open(inputFileName);
    inputStream = stream;
}

void App::run() {
    while (true) {
        if ((*inputStream).eof())
            break;

        string commandName;
        *inputStream >> commandName;



        if (commandName == ADD_APARTMENT)
            readAddApartmentCommand();
        else if (commandName == ADD_FLAT)
            readAddFlatCommand();
        else if (commandName == REMOVE_APARTMENT)
            readRemoveApartmentCommand();
        else if (commandName == MAKE_FLAT_EMPTY)
            readMakeFlatEmptyCommand();
        else if (commandName == FIND_SUM_OF_MAX_BANDWIDTH)
            writer->findSumOfMaxBandwidth();
        else if (commandName == MERGE_TWO_APARTMENTS)
            readMergeTwoApartmentsCommand();
        else if (commandName == RELOCATE_FLATS_TO_SAME_APARTMENT)
            readRelocateFlatsToSameApartmentCommand();
        else if (commandName == LIST_APARTMENTS)
            writer->listApartments();
    }
}

void App::readAddApartmentCommand() {
    string apartmentName;
    string position;
    int maxBandwidth;

    (*inputStream) >> apartmentName >> position >> maxBandwidth;

    if (position == HEAD) {
        street->addApartmentHead(apartmentName, maxBandwidth);
    }
    else {
        string positionIdentifier= position.substr(0, position.find('_'));
        string positionName = position.substr(position.find('_') + 1, position.size()+1);

        if(positionIdentifier == BEFORE)
            street->addApartmentBefore(apartmentName, positionName, maxBandwidth);
        else if (positionIdentifier == AFTER)
            street->addApartmentAfter(apartmentName, positionName, maxBandwidth);
    }
}

void App::readAddFlatCommand(){
    string apartmentName;
    int index;
    int initialBandwidth;
    int flatId;

    (*inputStream) >> apartmentName >> index >> initialBandwidth >> flatId;
    street->addFlat(apartmentName, index, flatId, initialBandwidth);
}

void App::readRemoveApartmentCommand() {
    string apartmentName;

    (*inputStream) >> apartmentName;

    street->removeApartment(apartmentName);
}

void App::readMakeFlatEmptyCommand() {
    string apartmentName;
    int flatId;

    (*inputStream) >> apartmentName >> flatId;
    street->makeFlatEmpty(apartmentName, flatId);
}

void App::readMergeTwoApartmentsCommand() {
    string apartmentName1;
    string apartmentName2;

    (*inputStream) >> apartmentName1 >> apartmentName2;
    street->mergeApartments(apartmentName1, apartmentName2);
}

void App::readRelocateFlatsToSameApartmentCommand() {
    string newApartmentName;
    int flatIdToShift;
    string flatIdArrStr;

    (*inputStream) >> newApartmentName >> flatIdToShift >> flatIdArrStr;

    // Split flatIdArrStr into a vector of flatIds
    vector<int> flatIds;
    string str;
    for(int i = 1; i < flatIdArrStr.size() - 1; i++) {
        if(flatIdArrStr[i] == COMMA) {
            flatIds.push_back(stoi(str));
            str = "";
            continue;
        }
        str += flatIdArrStr[i];
    }
    flatIds.push_back(stoi(str));
    // ----

    street->relocateFlats(newApartmentName, flatIdToShift, flatIds);

}

App::~App() {
    inputStream->close();
    delete inputStream;
}
