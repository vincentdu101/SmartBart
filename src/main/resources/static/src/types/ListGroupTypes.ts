import { IStation } from "./StationTypes";

export interface IListGroupInfoState {
    dropdownOpen: boolean
}

export interface IListGroupInfoProp {
    input: IStation[],
    label: string,
    selectionCallback: Function
}