import React, {useState, useEffect} from 'react';

// MUI
import {
    Card,
    CardMedia,
    List,
    ListItem,
    ListItemText,
    CardContent,
} from '@material-ui/core';

export default function EditProfileCurrentInfo() {
    const baseURL = "https://event-management-app-backend.herokuapp.com/";
    const baseURLTesting = "http://localhost:8080/";
    
    const [username, setUsername] = useState("");
    const [firstname, setFirstName] = useState("");
    const [lastname, setLastName] = useState("");
    const [email, setEmail] = useState("");
    const [birthday, setBirthday] = useState("");

    const userParams = {username,firstname,lastname,birthday,email};

    useEffect(() => {
        fetch(
            baseURLTesting + "users/" + localStorage.getItem('username'),
            {
                method: 'GET',
                mode: 'cors',
                headers: {
                    'Content-Type': 'application/json',
                    "Access-Control-Allow-Origin" : "*", 
                    "Access-Control-Allow-Credentials" : true 
                },
            }
            ).then((result) => {
            result.json().then((resp) => {
                setUsername(resp.username);
                setFirstName(resp.firstName);
                setLastName(resp.lastName);
                setEmail(resp.email);
                setBirthday((resp.birthday).substring(0,10));
            })
        }).catch(error => console.log("ERROR OCCURRED"))
    },[])

    let listItems = Object.values(userParams).map((item, idx) => {
        return (
            <ListItem
                key={idx}
                style={{ borderTop: idx !== 0 ? '1px solid #c4c4c4' : 'None' }}
            >
                <ListItemText primary={`${item}`} />
            </ListItem>
        );
    });
    return (
        <Card style={{ backgroundColor: '#f8fcff', borderRadius: '0%' }}>
            <CardMedia
                style={{
                    borderRadius: '50%',
                    objectFit: 'cover',
                    height: '200px',
                    width: '200px',
                }}
                component='img'
                image={require('../static/images/chad.jpeg')}
                alt='Chad'
            />
            <CardContent>
                <List
                    sx={{
                        width: '100%',
                        maxWidth: 360,
                        bgcolor: 'background.paper',
                    }}
                >
                    {listItems}
                </List>
            </CardContent>
        </Card>
    );
}
