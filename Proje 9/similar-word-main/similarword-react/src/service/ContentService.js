import http from "./http-common";

    const generateFromMultipleString = data => {
        return http.post(`/content`, data)
    };

    const getContents = () => {
        return http.get("content");
    };

    const getContentById = id => {
        return http.get(`/content/${id}`);
    };
    const saveContent = data => {
        return http.post(`/content/save`, data);
    };
    
    const ContentService = {
        generateFromMultipleString,
        getContents,
        getContentById,
        saveContent
    }

    export default ContentService;