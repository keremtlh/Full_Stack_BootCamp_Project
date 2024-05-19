import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import BlogCategoryApi from '../../services/BlogCategoryApi';

export default function BlogCategoryList(){

    // REDIRECT
    let navigate=useNavigate();

    // STATE
    const [blogCategoriesList,setBlogCategoriesList]=useState([]); //unutma list yazacaksınız.

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
                <tr>
                <td>1</td>
                <td>Task 1</td>
                <td>You have to study React today.If you dont do that you can lost everything.</td>
                <td>
                <button className="btn btn-primary">Update</button>
                </td>
                <td>
                <button className="btn btn-warning">View</button>
                </td>
                <td>
                <button className="btn btn-danger"><i class="fa-solid fa-trash-can"></i></button>
                </td>
                </tr>
            </tbody>

        </table>
        </>
    )
}