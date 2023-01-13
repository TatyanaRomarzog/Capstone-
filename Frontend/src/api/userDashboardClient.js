import BaseClass from "../util/baseClass";
import axios from 'axios'

export default class userDashboardClient extends BaseClass {

 constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', ];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

  async getAllJobPosts(errorCallback) {
        try {
            const response = await this.client.get(`/employer/{employerUsername}/jobpost/all`);
            return response.data;
        } catch(error) {
            this.handleError("getAllJobPosts", error, errorCallback);
        }
    }
