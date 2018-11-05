import { IStationInfo } from "./StationTypes";

export interface IListGroupInfoState {
    dropdownOpen: boolean
}

export interface IListGroupInfoProp {
    input: IStationInfo[],
    label: string,
    selectionCallback: Function
}