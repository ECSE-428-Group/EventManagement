import * as React from 'react';

// Router
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// Pages
import Signin from './pages/Signin';
import Signup from './pages/Signup';
import EditProfile from './pages/EditProfile';
import UserHome from './pages/UserHome';
import CreateEvent from './pages/CreateEvent';

// API
import { createAccount, editProfile } from './API';

function App() {
    const handleCreateAccount = (createAccountData) => {
        createAccount(createAccountData)
            .then(({ status, data }) => {
                if (status !== 200) {
                    // 200 indicates successful request
                    throw new Error('Account not created');
                }
            })
            .catch((error) => console.log(error));
        return 0;
    };

    const handleEditProfile = (editProfileData) => {
        let success = 1;
        editProfile(editProfileData)
            .then(console.log(status),({ status, data }) => {
                if (status !== 200) {
                    // 200 indicates successful request
                    success = 0;
                    throw new Error('Account not Edited');
                }
            })
            .catch((error) => console.log(error));
        return success;
    };

    return (
        <>
            <Router>
                <Routes>
                    <Route
                        exact
                        path='/signup'
                        element={
                            <Signup handleCreateAccount={handleCreateAccount} />
                        }
                    />
                    <Route exact path='/signin' element={<Signin />} />
                    <Route exact path='/userHome' element={<UserHome />} />
                    <Route
                        exact
                        path='/editProfile'
                        element={<EditProfile handleEditProfile={handleEditProfile} />}
                    />
                    <Route
                        exact
                        path='/createEvent'
                        element={<CreateEvent />}
                    />
                </Routes>
            </Router>
        </>
    );
}

export default App;
