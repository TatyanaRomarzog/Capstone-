import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import applicationClient from "../api/applicationClient";

class applicationPage extends BaseClass {

    constructor() {
            super();
            this.bindClassMethods(['onGet', 'onCreate', 'renderApplication'], this);
            this.dataStore = new DataStore();
        }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
            document.getElementById('get-by-id-form').addEventListener('submit', this.onGet);
            document.getElementById('create-form').addEventListener('submit', this.onCreate);
            this.client = new applicationClient();

            this.dataStore.addChangeListener(this.renderApplication)

        }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderApplication() {
            let resultArea = document.getElementById("result-info");

            const applications = this.dataStore.get("application");

            if (applications) {
                resultArea.innerHTML = `
                    <div>ID: ${application.applicationId}</div>
                    <div>Title: ${application.username}</div>
                    <div>Post Date: ${application.timeStamp}</div>
                `
            } else {
                resultArea.innerHTML = "No Item";
            }
        }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGet(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("id-field").value;

        let result = await this.client.getCase(id, this.errorHandler);
        this.dataStore.set("application", result);
        if (result) {
            this.showMessage(`Got ${result.FirstName}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("application", null);

        let FirstName = document.getElementById("create-FirstName-field").value;
        let LastName = document.getElementById("create-LastName-field").value;
        let Email = document.getElementById("create-Email-field").value;
        let timeDate = document.getElementById("create-time-date-field").value;
        let description = document.getElementById("create-description-field").value;
        let potentialSuspects = document.getElementById("create-potential-suspects-field").value;

        const createdApplication = await this.client.createApplication(title, author, description, location, timeDate, potentialSuspects, this.errorHandler);
        this.dataStore.set("case", createdCase);


        if (createdCase) {
            this.showMessage(`Created ${createdCase.title}!`)
        } else {
            this.errorHandler("Error creating! Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const casePage = new CasePage();
    casePage.mount();
};

window.addEventListener('DOMContentLoaded', main);