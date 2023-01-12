import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import userDashboardClient from "../api/userDashboardClient";

class userDashboardPage extends BaseClass {

    constructor() {
            super();
            this.bindClassMethods(['onGetAllJobPosts', 'renderJobPositions'], this);
            this.dataStore = new DataStore();
        }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
            document.getElementById('open-positions').addEventListener('click', this.onGetAllJobPosts);

            this.client = new userDashboardClient();

            this.dataStore.addChangeListener(this.renderJobPositions)

        }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderJobPositions() {
        let resultArea = document.getElementById("open-positions-all");

        const jobPosts = this.dataStore.get("getAllJobPosts");
        const toArray = Object.entries(jobPosts);
        console.log(toArray);
        resultArea.innerHTML = "";

        if (jobPosts) {
            const ul = document.createElement("ul");
            for (let i = 0; i < jobPosts.length; i++) {
                const li = document.createElement("li");
                console.log("inside the for loop " + jobPosts[i]);
                li.innerHTML = `
                 <div>Employer: ${jobPosts.employerUsername}</div>
//                 <div>Title: ${application.username}</div>
//                 <div>Post Date: ${application.timeStamp}</div>
//                 <div>ID: ${application.applicationId}</div>
//                 <div>Title: ${application.username}</div>
//                 <div>Post Date: ${application.timeStamp}</div>
//                 <div>ID: ${application.applicationId}</div>
//                 <div>Title: ${application.username}</div>
//                 <div>Post Date: ${application.timeStamp}</div>
                   `
                 ul.append(li);

            }
            resultArea.append(ul);
        }else {
            resultArea.innerHTML = "No Item";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetAllJobPosts(event) {
        console.log("Entering onGetAllJobPosts method")
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        const result = await this.client.getAllJobPosts(this.errorHandler);
        if (result && result.length > 0) {
            this.showMessage("Listing all Job Posts");
            this.dataStore.set("getAllJobPosts", result);
        } else {
            this.errorHandler("Error getting all Job Posts");
            console.log("Error getting all Job Posts");
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
    const userDashboardPage = new userDashboardPage();
    userDashboardPage.mount();
};

window.addEventListener('DOMContentLoaded', main);