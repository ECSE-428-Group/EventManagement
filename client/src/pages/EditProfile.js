import React from 'react';

// MUI
import { Grid, Card, Button} from '@material-ui/core';

// Router
import { useNavigate } from 'react-router-dom';

import EditProfileCurrentInfo from '../components/EditProfileCurrentInfo';
import EditProfileUpdateInfo from '../components/EditProfileUpdateInfo';

function EditProfile( {handleEditProfile}) {
    const navigate = useNavigate();
    return (
        <Grid
            style={{ height: '100vh' }}
            container
            direction='row'
            justifyContent='center'
            alignItems='center'
        >
            <Grid container direction='row' alignItems='center' item xs={6} style = {{minWidth: 600}}>
                <Card
                    style={{
                        width: 'inherit',
                        boxShadow: 'none',
                        border: '1px solid #c4c4c4',
                    }}
                >
                    <Grid container direction='row' alignItems='center'>
                        <Grid item xs={5}>
                            <EditProfileCurrentInfo />
                            <Button
                                onClick = {() => navigate('/userhome')} 
                                variant='outlined'
                                style={{ backgroundColor: '#6558f5', color: '#ffffff', marginTop: 10}}
                            >
                                Back to Home Page
                         </Button>
                        </Grid>
                        <Grid item xs={6} style={{ height: '100%' }}>
                            <EditProfileUpdateInfo 
                                handleEditProfile={handleEditProfile}
                            />
                        </Grid>
                    </Grid>
                </Card>
            </Grid>
        </Grid>
    );
}

export default EditProfile