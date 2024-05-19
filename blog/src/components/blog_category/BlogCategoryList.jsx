import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import BlogCategoryApi from '../../services/BlogCategoryApi';

export default function BlogCategoryList(){

    // REDIRECT
    let navigate=useNavigate();

    // STATE
    const [getBlogCategoryList,setBlogCategoriesList]=useState([]); //unutma list yazacaksınız.

     // EFFECT 
    useEffect( ()=>{
        fetchBlogCategoryList()
    },[]);

    const fetchBlogCategoryList=async()=>{
        try {
            const response= await BlogCategoryApi.categoryApiList();
            setBlogCategoriesList(response.data);
        } catch (error) {
            console.error(error)

        }
    }

    // RETURN
    return(
        <>
        <table className="table table-striped table-responsive mb-4">
            <thead>
                <tr>
                <th>Id</th>
                <th>Task Name</th>
                <th>Created Date</th>
                <th>Update</th>
                <th>View</th>
                <th>Delete</th>
                </tr> 
            </thead>
            <tbody>
{
    getBlogCategoryList.map((data)=>
    <tr>
        <td>{data.categoryId}</td>
        <td>{data.categoryName}</td>
        <td>{data.systemCreatedDate}</td>
        <td><button className="btn btn-warning" >Update</button></td>
        <td><button className="btn btn-info">View</button></td>
        <td><button className="btn btn-danger">Delete</button></td>
    </tr>
    )
}
                
            </tbody>

        </table>
        </>
    )
}