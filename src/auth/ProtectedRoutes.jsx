import React from 'react'
import { Navigate } from 'react-router-dom'
import { getAuthToken } from '../axios_helper';

const ProtectedRoutes = ({ path, childeren}) => {
    if (!getAuthToken()){
        return <Navigate to = {path} replace/>
    }
    return childeren;
}

export default ProtectedRoutes