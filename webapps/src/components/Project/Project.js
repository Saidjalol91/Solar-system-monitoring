import React from "react";
import CheckboxTree from "react-checkbox-tree";
import { useState } from "react";
import {Tree} from "primereact/tree";

const nodes = [
  {
    value: 'mars',
    label: 'Mars',
    children: [
      { value: 'phobos', label: 'Phobos' },
      { value: 'deimos', label: 'Deimos' },
    ],
  },
];


class Project extends React.PureComponent{

  state = {
    checked:[],
    expanded :[],
  };
  render(){
    return (
      <CheckboxTree
        nodes={nodes}
        checked={this.state.checked}
        expanded={this.state.expanded}
        onCheck={checked => this.setState({ checked })}
        onExpand={expanded => this.setState({ expanded })}
      />
    );
  }
};
export default Project;
