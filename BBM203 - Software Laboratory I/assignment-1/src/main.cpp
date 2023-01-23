#include <vector>
#include "Input.h"
#include "App.h"

using namespace std;


int main(__attribute__((unused)) int argc, char * argv[]) {
    Input *input = new Input(argv);
    App *app = new App(input);
    app->run();

    delete input;
    delete app;

    return 0;
}
