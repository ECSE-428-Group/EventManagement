import React from 'react';

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
    const items = ['Name', 'Password', 'Email', 'Location'];

    const listItems = items.map((item, idx) => {
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
