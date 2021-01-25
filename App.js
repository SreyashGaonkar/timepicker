

import React, { Component } from 'react';
import {
  StyleSheet,
  View,
  Text,
  TouchableOpacity,
  NativeModules
 
} from 'react-native';


const dateTimePicker = NativeModules.DateTimePicker;

class App extends Component {
  
  
  openDatePicker = ()=>{

    // dateTimePicker.showTimePicker((error,time)=>{
    //     if(error){
    //       console.log(`Error found! ${error}`);
    //     }else{
    //       console.log(time);
    //     }
    //   })

    // dateTimePicker.showDatePickerDialog((error,date)=>{
    //   if(error){
    //     console.log(`Error found! ${error}`);
    //   }else{
    //     console.log(date);
    //   }
    // })
   
    dateTimePicker.showCustomeDatePickerDialog('22/01/2020','29/01/2021',(error,date)=>{
      if (error) {
        console.log(`Error found! ${error}`);
      }else{
        console.log(date);
      }
    });
  }
  render(){

  
  return (
    <View style={styles.constainer}>
      <TouchableOpacity onPress = {()=> this.openDatePicker()} style={styles.button}>
        <Text>click</Text>
      </TouchableOpacity>
    </View>
  );
}
};

const styles = StyleSheet.create({
  constainer:{
    flex:1,
    alignItems:'center',
    justifyContent:'center',
   
  },
  button:{
    borderRadius:5,
    backgroundColor:'blue',
    paddingHorizontal:15,
    paddingVertical:5
  }
});

export default App;
