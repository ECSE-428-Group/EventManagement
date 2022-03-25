import React from 'react';

// MUI
import { Grid, Card } from '@material-ui/core';

import EditProfileCurrentInfo from '../components/EditProfileCurrentInfo';
import EditProfileUpdateInfo from '../components/EditProfileUpdateInfo';

function EditProfile( {handleEditProfile}) {
    return (
        <Grid
            style={{ height: '100vh' }}
            container
            direction='row'
            justifyContent='center'
            alignItems='center'
        >
            <Grid container direction='row' alignItems='center' item xs={10}>
                <Card
                    style={{
                        width: 'inherit',
                        boxShadow: 'none',
                        border: '1px solid #c4c4c4',
                    }}
                >
                    <Grid container direction='row' alignItems='center'>
                        <Grid item xs={6}>
                            <EditProfileCurrentInfo />
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