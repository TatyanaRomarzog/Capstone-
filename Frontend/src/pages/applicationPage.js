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

        let result = await this.client.getApplication(id, this.errorHandler);
        this.dataStore.set("application", result);
        if (result) {
            this.showMessage(`Got ${result.firstName}!`)
        } else {
            this.errorHandler("Error doing GET!  Try again...");
        }
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        this.dataStore.set("application", null);

        let firstName = document.getElementById("create-firstName-field").value;
        let lastName = document.getElementById("create-lastName-field").value;
        let homeAddress = document.getElementById("create-homeAddress-field").value;
        let phoneNumber = document.getElementById("create-phoneNumber-field").value;
        let emailAddress = document.getElementById("create-emailAddress-field").value;
        let objective = document.getElementById("create-objective-field").value;
        let education = document.getElementById("create-education-field").value;
        let experience = document.getElementById("create-experience-field").value;
        let skills = document.getElementById("create-skills-field").value;
        let workHistory = document.getElementById("create-workHistory-field").value;
        let positionTitle = document.getElementById("create-positionTitle-field").value;
        let locations = document.getElementById("create-locations-field").value;
        let minimumSalary = document.getElementById("create-minimumSalary-field").value;
        let openJobsLimit = document.getElementById("create-openJobsLimit-field").value;



        const createdApplication = await this.client.createApplication(firstName, lastName,  homeAddress, phoneNumber, emailAddress, objective, education, experience, skills, workHistory, positionTitle, locations, minimumSalary, openJobsLimit, this.errorHandler);
        this.dataStore.set("application", createdApplication);


        if (createdApplication) {
            this.showMessage(`Created ${createdApplication.applicationId}!`)
        } else {
            this.errorHandler("Error creating! Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const applicationPage = new ApplicationPage();
    applicationPage.mount();
};

window.addEventListener('DOMContentLoaded', main);